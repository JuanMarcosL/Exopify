package com.example.spotify_pirata.Navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.spotify_pirata.BarraInferior
import com.example.spotify_pirata.BarraSuperior
import com.example.spotify_pirata.PantallaDeReproduccion
import com.example.spotify_pirata.PantallaLogin
import com.example.spotify_pirata.ScaffoldViewModel
import com.example.spotify_pirata.ViewModelListaReproduccion

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationGraph() {

    val navController = rememberNavController()

    val viewModelScaffold : ScaffoldViewModel = viewModel()

    val exoPlayerViewModel: ViewModelListaReproduccion = viewModel()
    val contexto = LocalContext.current
    var isLightMode = exoPlayerViewModel.isLightMode.collectAsState()


    Scaffold(topBar = { BarraSuperior(titulo = "Reproductor de prueba", isLightMode.value, exoPlayerViewModel)},
        bottomBar = { BarraInferior(funcionreproductor = {
                      exoPlayerViewModel.crearReproductor(contexto)
            }, exoPlayerViewModel,isLightMode.value)},
        content = {
            // paddingValues representa los dp que hay para evitar que el contenido se solape con las barras
                paddingValues ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                color = Color.DarkGray
            ) {
                NavHost(navController = navController, startDestination = Routs.PantallaDeReproduccion.rout){

                    composable(Routs.Login.rout) { PantallaLogin(navController = navController, exoPlayerViewModel) }
                    composable(Routs.PantallaDeReproduccion.rout) { PantallaDeReproduccion(
                        navController = navController, exoPlayerViewModel) }
                }
            }

        })

}
