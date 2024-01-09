package com.example.spotify_pirata.model

data class Playlist(
    val nombre: String,
    val imagen: String,
    val canciones: ArrayList<Cancion>
)