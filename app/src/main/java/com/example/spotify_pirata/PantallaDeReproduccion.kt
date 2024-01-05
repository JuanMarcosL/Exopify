package com.example.spotify_pirata

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

val LocalIsDarkTheme = compositionLocalOf { false }

@Composable
fun MyAppTheme(
    isDarkTheme: Boolean = LocalIsDarkTheme.current,
    content: @Composable () -> Unit,
) {
    val colorScheme = if (isDarkTheme) darkColorScheme() else lightColorScheme()


}
@Composable
fun PantallaDeReproduccion(navController: NavHostController) {

    val exoPlayerViewModel: ViewModelListaReproduccion = viewModel()
    val contexto = LocalContext.current

    val corutinaScope = rememberCoroutineScope()
    LaunchedEffect(key1 = Unit) {
        corutinaScope.launch {
            exoPlayerViewModel.crearReproductor(contexto)
        }
    }

    var isRepeatOn by remember { mutableStateOf(false) }
    var isShuffleOn by remember { mutableStateOf(false) }
    var isPlaying by remember { mutableStateOf(false) }


    var isLightMode by remember { mutableStateOf(false) }


    val temaModifier = Modifier
        .background(if (isLightMode) Color.White else Color.DarkGray)
        .padding(16.dp)

    val iconTint = if (isLightMode) Color.Black else Color.White

    val temaIcon = if (isLightMode) {
        painterResource(id = R.drawable.dark_mode_fill0_wght400_grad0_opsz24)

    } else {
        painterResource(id = R.drawable.light_mode_fill0_wght400_grad0_opsz24)
    }

    Column(
        modifier = Modifier
            .background(color = if (isLightMode) Color.White else Color.DarkGray)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Icon(
                painter = temaIcon,
                contentDescription = "Mode",
                tint = iconTint,
                modifier = temaModifier.clickable {
                    // Cambiar el estado y el tema al hacer clic
                    isLightMode = !isLightMode
                }
            )
        }
        Column(modifier = Modifier.fillMaxWidth().fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            Text(
                text = "Reproduciendo ahora",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                color = iconTint,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

            Image(
                painter = painterResource(id = R.drawable.album_karol),
                contentDescription = "Album de la karol G",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
            )

            Column(modifier = Modifier.padding(start = 20.dp, end = 20.dp)) {
                val canciones = exoPlayerViewModel.canciones.collectAsState()
                val index = exoPlayerViewModel.index.collectAsState()

                var nombreCancion = canciones.value[index.value].nombre
                var nombreModificado = nombreCancion.replace("_", " ")

                Text(
                    text = nombreModificado,
                    color = iconTint,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )

                var nombreArtista = canciones.value[index.value].artista

                Text(
                    text = nombreArtista,
                    color = iconTint
                )

                Column(modifier = Modifier.padding(top = 50.dp)) {
                    Slider(value = 1f, onValueChange = { })
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {


                        Text(
                            text = "0:00",
                            color = iconTint
                        )
                        Text(
                            text = "3:00",
                            color = iconTint
                        )
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.shuffle_fill0_wght400_grad0_opsz24),
                    contentDescription = "Random",
                    tint = if (isShuffleOn) Color.Green else iconTint,
                    modifier = Modifier.clickable {2
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
}