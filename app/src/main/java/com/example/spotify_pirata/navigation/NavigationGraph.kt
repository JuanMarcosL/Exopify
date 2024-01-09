package com.example.spotify_pirata.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.spotify_pirata.gui.BarraInferior
import com.example.spotify_pirata.gui.BarraSuperior
import com.example.spotify_pirata.gui.PantallaDeReproduccion
import com.example.spotify_pirata.view_model.ViewModelListaReproduccion

@Composable
fun NavigationGraph() {
    val navController = rememberNavController()
    val exoPlayerViewModel: ViewModelListaReproduccion = viewModel()
    val isLightMode = exoPlayerViewModel.isLightMode.collectAsState()

    Scaffold(
        topBar = {
                BarraSuperior(
                    isLightMode.value,
                    exoPlayerViewModel,
                    navController
                )
        },
        bottomBar = {
            BarraInferior(exoPlayerViewModel, isLightMode.value)
        },
        content = { paddingValues ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                color = Color.DarkGray
            ) {
                NavHost(
                    navController = navController,
                    startDestination = Routs.PantallaDeReproduccion.rout
                ) {
                    composable(Routs.PantallaDeReproduccion.rout) {
                        PantallaDeReproduccion(
                            navController = navController, exoPlayerViewModel
                        )
                    }
                }
            }
        }
    )
}

