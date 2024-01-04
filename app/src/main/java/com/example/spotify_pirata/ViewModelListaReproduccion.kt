package com.example.spotify_pirata

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.spotify_pirata.Utiles.Companion.obtenerRuta
import kotlinx.coroutines.launch

class ViewModelListaReproduccion : ViewModel() {
    private var _reproductor : MutableStateFlow<ExoPlayer?> = MutableStateFlow(null)
    val reproductor = _reproductor.asStateFlow()

    private var _canciones = MutableStateFlow(
        listOf(
            Cancion("chop_suey", "", ""),
            Cancion("the_fat_rat_rise_up", "", ""),
        )
    )
    val canciones = _canciones.asStateFlow()
    var index = 0
    var reproduciendo = false
    var numeroClics = 0

    fun reproducirLista(contexto: Context) {
        _reproductor.value!!.addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                if (playbackState == Player.STATE_READY) {
                    _reproductor.value!!.playWhenReady = reproduciendo
                    println(reproduciendo)
                } else if (playbackState == Player.STATE_ENDED) {
                    index++
                    if (index > _canciones.value.lastIndex) {
                        index = 0
                    }
                    val mediaItem = MediaItem.fromUri(obtenerRuta(contexto, _canciones.value[index].nombre))
                    _reproductor.value!!.setMediaItem(mediaItem)
                }
            }
        })
    }

    fun clicReproducir(contexto: Context) {
        if (numeroClics == 0) {
            _reproductor.value = ExoPlayer.Builder(contexto).build()
            reproductor.value!!.prepare()
            val mediaItem = MediaItem.fromUri(obtenerRuta(contexto, _canciones.value[0].nombre))
            _reproductor.value!!.setMediaItem(mediaItem)

            viewModelScope.launch {
                reproducirLista(contexto)
            }
        }
        reproduciendo = !reproduciendo
        numeroClics++
    }
}