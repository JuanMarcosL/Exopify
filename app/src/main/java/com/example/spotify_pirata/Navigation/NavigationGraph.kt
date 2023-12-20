package com.example.spotify_pirata.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.spotify_pirata.PantallaLogin

@Composable
fun NavigationGraph() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routs.Login.rout){

        composable(Routs.Login.rout) { PantallaLogin(navController = navController) }
    }

}
