package com.example.spotify_pirata

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.spotify_pirata.Utiles.Companion.obtenerRuta

class ViewModelListaReproduccion : ViewModel() {
    private var _reproductor : MutableStateFlow<ExoPlayer?> = MutableStateFlow(null)
    val reproductor = _reproductor.asStateFlow()

    fun crearExoPlayer(context : Context) {
        _reproductor.value = ExoPlayer.Builder(context).build()
        reproductor.value!!.prepare()
    }

    fun reproducir(context : Context) {
        val mediaItem = MediaItem.fromUri(obtenerRuta(context, "clair_de_lune"))
        _reproductor.value!!.setMediaItem(mediaItem)
        _reproductor.value!!.playWhenReady = true
    }
}