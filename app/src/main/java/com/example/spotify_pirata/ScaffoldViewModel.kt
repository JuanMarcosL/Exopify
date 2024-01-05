package com.example.spotify_pirata


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ScaffoldViewModel : ViewModel() {


    private val _titulo = MutableStateFlow("Player")
    val titulo = _titulo.asStateFlow()

    fun modificarTitulo(nuevoTitulo : String){
        _titulo.value = nuevoTitulo
    }

}