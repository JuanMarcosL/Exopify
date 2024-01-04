package com.example.spotify_pirata

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun PantallaDeReproduccion(navController: NavHostController) {

    Column (verticalArrangement = Arrangement.SpaceEvenly){
        Text(text = "Reproduciendo ahora",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth())
        Text(text = "Nombre de la canción - Nombre del artista")
        Image(painter = painterResource(id = R.drawable.album_karol),
            contentDescription = "Album de la karol G",
            modifier = Modifier.fillMaxWidth()
                .height(350.dp)
            )
        Column() {
            Slider(value = 1f, onValueChange = { })
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "0:00")
                Text(text = "3:00")
            }
        }
        Row (modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly){

            Icon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = "Random"
            )

            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Anterior"
            )
            Icon(
                imageVector = Icons.Filled.PlayArrow,
                contentDescription = "Play"
            )
            Icon(
                imageVector = Icons.Filled.ArrowForward,
                contentDescription = "Siguiente"
            )

            Icon(
                imageVector = Icons.Filled.Clear,
                contentDescription = "Repetir"
            )
        }


    }
}