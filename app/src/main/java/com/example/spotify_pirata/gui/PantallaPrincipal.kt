package com.example.spotify_pirata.gui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.spotify_pirata.model.DataUp
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
                    val discos = DataUp.recopilarDiscos()
                    LazyRow {
                        items(discos) { disco ->
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .padding(horizontal = 8.dp)
                                    .clickable {

                                    }
                            ) {
                                val resourceId = contexto.resources.getIdentifier(
                                    disco.imagen,
                                    "drawable",
                                    contexto.packageName
                                )
                                Image(
                                    painter = painterResource(id = resourceId),
                                    contentDescription = "Album: ${disco.nombre}",
                                    modifier = Modifier
                                        .height(125.dp)
                                        .fillMaxHeight()
                                        .aspectRatio(1f)
                                        .scale(1f)
                                )
                                Text(disco.nombre)
                                Text(disco.grupo)
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(50.dp))
                    Text(
                        text = "Escucha tus listas de reproducción",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = iconTint
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    val playlists = DataUp.recopilarPlaylist()
                    LazyRow {
                        items(playlists) { playlist ->
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .padding(horizontal = 8.dp)
                                    .clickable {

                                    }
                            ) {
                                val resourceId = contexto.resources.getIdentifier(
                                    playlist.imagen,
                                    "drawable",
                                    contexto.packageName
                                )
                                Image(
                                    painter = painterResource(id = resourceId),
                                    contentDescription = "Playlist: ${playlist.nombre}",
                                    modifier = Modifier
                                        .height(125.dp)
                                        .fillMaxHeight()
                                        .aspectRatio(1f)
                                        .scale(1f)
                                )
                                Text(playlist.nombre)
                                Text("${playlist.canciones.size} canciones")
                            }
                        }
                    }
                }
            }
        }
    )
}