package com.example.spotify_pirata.model

data class Disco (
    val nombre: String,
    val grupo: String,
    val imagen: String,
    val fechaLanzamiento: Int,
    val canciones: ArrayList<Cancion>
)