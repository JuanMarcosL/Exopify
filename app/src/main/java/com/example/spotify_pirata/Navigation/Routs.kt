package com.example.spotify_pirata.Navigation

sealed class Routs (val rout: String){

    object Login : Routs("login")
    object PantallaDeReproduccion : Routs("pantallaDeReproduccion")
}
