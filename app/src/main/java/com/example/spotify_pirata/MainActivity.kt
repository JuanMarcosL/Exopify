package com.example.spotify_pirata

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.spotify_pirata.Navigation.NavigationGraph
import com.example.spotify_pirata.ui.theme.Spotify_PirataTheme

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

                    NavigationGraph()
                }
            }
        }
    }
}

val LocalIsDarkTheme = compositionLocalOf { false }

@Composable
fun MyAppTheme(
    isDarkTheme: Boolean = LocalIsDarkTheme.current,
    content: @Composable () -> Unit,
) {
    val colorScheme = if (isDarkTheme) darkColorScheme() else lightColorScheme()
}
