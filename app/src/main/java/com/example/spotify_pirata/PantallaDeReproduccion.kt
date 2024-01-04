package com.example.spotify_pirata

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun PantallaDeReproduccion(navController: NavHostController) {

    val exoPlayerViewModel: ViewModelListaReproduccion = viewModel()
    val contexto = LocalContext.current

    var isRepeatOn by remember { mutableStateOf(false) }
    var isShuffleOn by remember { mutableStateOf(false) }
    var isPlaying by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .wrapContentSize(Alignment.TopEnd)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.light_mode_fill0_wght400_grad0_opsz24),
            contentDescription = "modo claro",
            tint = Color.White
        )
    }
    Column(modifier = Modifier.background(color = Color.DarkGray),
        verticalArrangement = Arrangement.SpaceEvenly) {


                Text(
                    text = "Reproduciendo ahora",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.White,
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
                    Text(
                        text = "Nombre de la canción",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                    Text(
                        text = "Nombre del artista",
                        color = Color.White
                    )

                    Column(modifier = Modifier.padding(top = 50.dp)) {
                        Slider(value = 1f, onValueChange = { })
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "0:00",
                                color = Color.White
                            )
                            Text(
                                text = "3:00",
                                color = Color.White
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
                        tint = if (isShuffleOn) Color.Green else Color.White,
                        modifier = Modifier.clickable { isShuffleOn = !isShuffleOn }
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.arrow_back_ios_fill0_wght400_grad0_opsz24),
                        contentDescription = "Anterior",
                        tint = Color.White
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
                        tint = Color.White,

                        )

                    Icon(
                        painter = painterResource(id = R.drawable.arrow_forward_ios_fill0_wght400_grad0_opsz24),
                        contentDescription = "Siguiente",
                        tint = Color.White
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.repeat_fill0_wght400_grad0_opsz24),
                        contentDescription = "Repetir",
                        tint = if (isRepeatOn) Color.Green else Color.White,
                        modifier = Modifier.clickable { isRepeatOn = !isRepeatOn }
                    )
                }
            }
        }

//}