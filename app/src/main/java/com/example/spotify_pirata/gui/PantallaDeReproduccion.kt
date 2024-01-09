package com.example.spotify_pirata.gui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.spotify_pirata.view_model.ViewModelListaReproduccion

@Composable
fun PantallaDeReproduccion(
    navController: NavHostController,
    exoPlayerViewModel: ViewModelListaReproduccion = viewModel()
) {

    val contexto = LocalContext.current
    val isLightMode = exoPlayerViewModel.isLightMode.collectAsState()
    val iconTint = if (isLightMode.value) Color.Black else Color.White
    val canciones = exoPlayerViewModel.canciones.collectAsState()
    val index = exoPlayerViewModel.index.collectAsState()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val searchBarAbierta = exoPlayerViewModel.searchBarAbierta.collectAsState()

    LaunchedEffect(key1 = searchBarAbierta.value){
        if (searchBarAbierta.value){
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
                    .background(color = if (isLightMode.value) Color.White else Color.DarkGray)
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    val nombreCancion = canciones.value[index.value].nombre
                    val nombreModificado = nombreCancion.replace("_", " ")

                    Text(
                        text = "Reproduciendo ahora",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                        color = iconTint,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                    val resourceId = contexto.resources.getIdentifier(
                        nombreCancion,
                        "drawable",
                        contexto.packageName
                    )
                    Image(
                        painter = painterResource(id = resourceId),
                        contentDescription = "Album de $nombreCancion",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(350.dp)
                    )

                    Column(modifier = Modifier.padding(start = 20.dp, end = 20.dp)) {

                        Text(
                            text = nombreModificado,
                            color = iconTint,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )

                        val nombreArtista = canciones.value[index.value].artista

                        Text(
                            text = nombreArtista,
                            color = iconTint
                        )

                        Column(modifier = Modifier.padding(top = 50.dp)) {

                            val duracionMinutos by exoPlayerViewModel.duracionMinutos.collectAsStateWithLifecycle()
                            val duracionSegundos by exoPlayerViewModel.duracionSegundos.collectAsStateWithLifecycle()
                            val posicionSegundos by exoPlayerViewModel.posicionSegundos.collectAsStateWithLifecycle()
                            val posicionMinutos by exoPlayerViewModel.posicionMinutos.collectAsStateWithLifecycle()

                            var progreso by remember { mutableStateOf(0f) }

                            if (duracionMinutos > 0 && duracionSegundos >= 0 && posicionMinutos >= 0 && posicionSegundos >= 0) {
                                progreso = ((posicionMinutos * 60) + posicionSegundos).toFloat() /
                                        ((duracionMinutos * 60) + duracionSegundos).toFloat()
                            }

                            Slider(
                                value = progreso,
                                onValueChange = { nuevoProgreso ->
                                    progreso = nuevoProgreso
                                    val nuevaPosicionSegundos =
                                        (progreso * ((duracionMinutos * 60) + duracionSegundos)).toInt()
                                    exoPlayerViewModel.desplazarSlider(nuevaPosicionSegundos)
                                },
                                valueRange = 0f..1f,
                                steps = if (duracionMinutos * 60 + duracionSegundos > 0 ) duracionMinutos * 60 + duracionSegundos else 0,
                                colors = SliderDefaults.colors(
                                    thumbColor = Color.Green
                                )
                            )

                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {

                                Text(
                                    text = if (posicionSegundos < 10) "$posicionMinutos:0$posicionSegundos" else "$posicionMinutos:$posicionSegundos",
                                    color = iconTint
                                )
                                Text(
                                    text = if (duracionSegundos < 10) "$duracionMinutos:0$duracionSegundos" else "$duracionMinutos:$duracionSegundos",
                                    color = iconTint
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}
