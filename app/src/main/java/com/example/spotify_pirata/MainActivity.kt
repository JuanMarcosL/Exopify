package com.example.spotify_pirata

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.spotify_pirata.navigation.NavigationGraph
import com.example.spotify_pirata.ui.theme.Spotify_PirataTheme
import com.example.spotify_pirata.view_model.ViewModelListaReproduccion
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Spotify_PirataTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val exoPlayerViewModel: ViewModelListaReproduccion = viewModel()
                    val contexto = LocalContext.current
                    val corutinaScope = rememberCoroutineScope()

                    LaunchedEffect(key1 = Unit) {
                        corutinaScope.launch {
                            exoPlayerViewModel.crearReproductor(contexto)
                        }
                    }
                    NavigationGraph()
                }
            }
        }
    }
}
