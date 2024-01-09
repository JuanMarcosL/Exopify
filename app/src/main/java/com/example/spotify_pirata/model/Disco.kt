package com.example.spotify_pirata.model

data class Disco (
    val nombre: String,
    val grupo: String,
    val fechaLanzamiento: Int,
    val canciones: List<Cancion>
)