package com.example.spotify_pirata.gui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.spotify_pirata.navigation.Routs
import com.example.spotify_pirata.view_model.ViewModelListaReproduccion

@Composable
fun PantallaPrincipal(
    navController: NavController,
    exoPlayerViewModel: ViewModelListaReproduccion
) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val isLightMode = exoPlayerViewModel.isLightMode.collectAsState()
    val contexto = LocalContext.current
    val searchBarAbierta = exoPlayerViewModel.searchBarAbierta.collectAsState()
    val iconTint = if (isLightMode.value) Color.Black else Color.White

    LaunchedEffect(key1 = searchBarAbierta.value) {
        if (searchBarAbierta.value) {
            drawerState.open()
        } else {
            drawerState.close()
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            SearchBar(isLightMode.value) {
                exoPlayerViewModel.seleccionarCancion(contexto, it)
                exoPlayerViewModel.cambiarSearchBar()
            }
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = if (isLightMode.value) Color.White else Color.DarkGray),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                ) {

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Escucha la música que te gusta",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = iconTint
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Escucha tus listas de reproducción",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = iconTint
                    )

                }

            }

        }
    )
}