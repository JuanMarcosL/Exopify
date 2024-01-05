package com.example.spotify_pirata

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.unit.dp


@Composable
fun BarraInferior(
    funcionreproductor: () -> Unit,
    exoPlayerViewModel: ViewModelListaReproduccion,
    isLightMode: Boolean
) {

    val contexto = LocalContext.current

    var isRepeatOn by remember { mutableStateOf(false) }
    var isShuffleOn by remember { mutableStateOf(false) }
    var isPlaying by remember { mutableStateOf(false) }
    //var isLightMode = exoPlayerViewModel.isLightMode.collectAsState()

    val temaModifier = Modifier
        .background(if (isLightMode) Color.White else Color.DarkGray)
        .padding(16.dp)

    val iconTint = if (isLightMode) Color.Black else Color.White

    val temaIcon = if (isLightMode) {
        painterResource(id = R.drawable.dark_mode_fill0_wght400_grad0_opsz24)

    } else {
        painterResource(id = R.drawable.light_mode_fill0_wght400_grad0_opsz24)
    }

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
                    exoPlayerViewModel.clicAleatorio(contexto = contexto)
                }
            )
            Icon(
                painter = painterResource(id = R.drawable.arrow_back_ios_fill0_wght400_grad0_opsz24),
                contentDescription = "Anterior",
                tint = iconTint,
                modifier = Modifier.clickable { exoPlayerViewModel.clicAnterior(contexto = contexto) }
            )

            val playIcon = rememberVectorPainter(image = Icons.Filled.PlayArrow)
            val pauseIcon =
                painterResource(id = R.drawable.pause_fill0_wght400_grad0_opsz24)

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
    titulo: String,
    isLightMode: Boolean,
    exoPlayerViewModel: ViewModelListaReproduccion
) {
    var contexto = LocalContext.current

    val temaModifier = Modifier
        .background(if (isLightMode) Color.White else Color.DarkGray)
        .padding(16.dp)

    val iconTint = if (isLightMode) Color.Black else Color.White
    val temaIcon = if (isLightMode) {
        painterResource(id = R.drawable.dark_mode_fill0_wght400_grad0_opsz24)
    } else {
        painterResource(id = R.drawable.light_mode_fill0_wght400_grad0_opsz24)
    }

    CenterAlignedTopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SearchBar(isLightMode) { exoPlayerViewModel.seleccionarCancion(contexto, it) }
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