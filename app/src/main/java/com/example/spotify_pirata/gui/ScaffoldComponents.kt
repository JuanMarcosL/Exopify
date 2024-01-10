package com.example.spotify_pirata.gui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.spotify_pirata.R
import com.example.spotify_pirata.view_model.ViewModelReproduccion


@Composable
fun BarraInferior(
    exoPlayerViewModel: ViewModelReproduccion,
    isLightMode: Boolean
) {

    val contexto = LocalContext.current
    var isRepeatOn by remember { mutableStateOf(false) }
    var isShuffleOn by remember { mutableStateOf(false) }
    var isPlaying by remember { mutableStateOf(false) }
    val iconTint = if (isLightMode) Color.Black else Color.White

    BottomAppBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = if (isLightMode) Color.White else Color.DarkGray
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                painter = painterResource(id = R.drawable.shuffle_fill0_wght400_grad0_opsz24),
                contentDescription = "Random",
                tint = if (isShuffleOn) Color.Green else iconTint,
                modifier = Modifier.clickable {
                    isShuffleOn = !isShuffleOn
                    exoPlayerViewModel.clicAleatorio()
                }
            )
            Icon(
                painter = painterResource(id = R.drawable.arrow_back_ios_fill0_wght400_grad0_opsz24),
                contentDescription = "Anterior",
                tint = iconTint,
                modifier = Modifier.clickable { exoPlayerViewModel.clicAnterior(contexto = contexto) }
            )

            val playIcon = rememberVectorPainter(image = Icons.Filled.PlayArrow)
            val pauseIcon = painterResource(id = R.drawable.pause_fill0_wght400_grad0_opsz24)

            val playPauseIcon = if (isPlaying) pauseIcon else playIcon

            Icon(
                painter = playPauseIcon,
                contentDescription = if (isPlaying) "Pause" else "Play",
                modifier = Modifier
                    .clickable {
                        isPlaying = !isPlaying
                        exoPlayerViewModel.clicReproducir(contexto = contexto)
                    }
                    .size(60.dp),
                tint = iconTint,

                )

            Icon(
                painter = painterResource(id = R.drawable.arrow_forward_ios_fill0_wght400_grad0_opsz24),
                contentDescription = "Siguiente",
                tint = iconTint,
                modifier = Modifier.clickable { exoPlayerViewModel.clicSiguiente(contexto = contexto) }
            )

            Icon(
                painter = painterResource(id = R.drawable.repeat_fill0_wght400_grad0_opsz24),
                contentDescription = "Repetir",
                tint = if (isRepeatOn) Color.Green else iconTint,
                modifier = Modifier.clickable {
                    isRepeatOn = !isRepeatOn
                    exoPlayerViewModel.clicBucle()
                }
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraSuperior(
    isLightMode: Boolean,
    exoPlayerViewModel: ViewModelReproduccion,
    navController: NavController
) {
    val contexto = LocalContext.current

    val temaModifier = Modifier
        .background(if (isLightMode) Color.White else Color.DarkGray)
        .padding(16.dp)

    val iconTint = if (isLightMode) Color.Black else Color.White
    val temaIcon = if (isLightMode) {
        painterResource(id = R.drawable.dark_mode_fill0_wght400_grad0_opsz24)
    } else {
        painterResource(id = R.drawable.light_mode_fill0_wght400_grad0_opsz24)
    }

    //var isSearchPanelOpen by remember { mutableStateOf(false) }

    CenterAlignedTopAppBar(
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        painterResource(id = R.drawable.home_fill0_wght400_grad0_opsz24),
                        contentDescription = "Botón para volver a la pantalla principal",
                        modifier = Modifier
                           // .size(30.dp)
                            .clickable {
                                navController.navigate("pantallaPrincipal")
                            },
                        tint = iconTint
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Lupita",
                        tint = iconTint,
                        modifier = Modifier.clickable {
                            exoPlayerViewModel.cambiarSearchBar()
                        }
                    )
                }
                Text(
                    text = "ChaoticTunes",
                    fontFamily = androidx.compose.ui.text.font.FontFamily.Cursive,
                    fontSize = 36.sp,
                    color = iconTint,
                  fontWeight = FontWeight.Bold
                )

                Icon(
                    painter = temaIcon,
                    contentDescription = "Mode",
                    tint = iconTint,
                    modifier = temaModifier.clickable {
                        exoPlayerViewModel.cambiarModo()
                    }
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = if (isLightMode) Color.White else Color.DarkGray
        )
    )
}