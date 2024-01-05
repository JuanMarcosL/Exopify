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
import java.util.concurrent.TimeUnit

class ViewModelListaReproduccion : ViewModel() {
    private var _reproductor: MutableStateFlow<ExoPlayer?> = MutableStateFlow(null)
    val reproductor = _reproductor.asStateFlow()

    private var _canciones = MutableStateFlow(
        listOf(
            Cancion("chop_suey", "", "System of a Down"),
            Cancion("rise_up", "", "TheFatRat"),
            Cancion("respira_el_momento", "", "Calle 13"),
            Cancion("pollito_pio", "", "Pulcino Pio"),
            Cancion("telefono_carpintero", "", "Las ardillitas de Lalo Guerrero"),
        )
    )
    val canciones = _canciones.asStateFlow()

    private var _index = MutableStateFlow(0)
    val index = _index.asStateFlow()

    private var _bucle = MutableStateFlow(false)
    val bucle = _bucle.asStateFlow()

    private var _random = MutableStateFlow(false)
    val random = _random.asStateFlow()

    private var _duracionMinutos = MutableStateFlow( 0)
    val duracionMinutos = _duracionMinutos.asStateFlow()

    private var _duracionSegundos = MutableStateFlow( 0)
    val duracionSegundos = _duracionSegundos.asStateFlow()

    private var _posicionMinutos = MutableStateFlow( 0)
    val posicionMinutos = _posicionMinutos.asStateFlow()

    private var _posicionSegundos = MutableStateFlow( 0)
    val posicionSegundos = _posicionSegundos.asStateFlow()

    var reproduciendo = false
    var numeroClics = 0

    fun reproducirLista(contexto: Context) {
        _reproductor.value!!.addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                if (playbackState == Player.STATE_READY) {
                    val duracionMs = _reproductor.value!!.duration
                    _duracionMinutos.value = TimeUnit.MILLISECONDS.toMinutes(duracionMs).toInt()
                    _duracionSegundos.value = (TimeUnit.MILLISECONDS.toSeconds(duracionMs) % 60).toInt()

                    val posicionMs = _reproductor.value!!.currentPosition
                    _posicionMinutos.value = TimeUnit.MILLISECONDS.toMinutes(posicionMs).toInt()
                    _posicionSegundos.value = (TimeUnit.MILLISECONDS.toSeconds(posicionMs) % 60).toInt()
                }
                if (playbackState == Player.STATE_ENDED) {
                    if (_random.value) {
                        var temporal = (Math.random() * _canciones.value.size - 1).toInt()
                        if (temporal >= _index.value) {
                            temporal++
                        }
                        _index.value = temporal
                    } else {
                        _index.value++
                        if (_index.value > _canciones.value.lastIndex) {
                            _index.value = 0
                        }
                    }
                    val mediaItem = MediaItem.fromUri(
                        obtenerRuta(contexto,_canciones.value[_index.value].nombre)
                    )
                    _reproductor.value!!.setMediaItem(mediaItem)
                }

            }
        })
    }

    fun crearReproductor(contexto: Context) {
        _reproductor.value = ExoPlayer.Builder(contexto).build()
        reproductor.value!!.prepare()
        val mediaItem = MediaItem.fromUri(obtenerRuta(contexto, _canciones.value[0].nombre))
        _reproductor.value!!.setMediaItem(mediaItem)
    }

    fun clicReproducir(contexto: Context) {
        if (numeroClics == 0) {
            viewModelScope.launch {
                reproducirLista(contexto)
            }
        }
        reproduciendo = !reproduciendo
        _reproductor.value!!.playWhenReady = reproduciendo
        numeroClics++
    }

    fun clicAleatorio(contexto: Context) {
        _random.value = !_random.value
    }

    fun clicBucle() {
        _bucle.value = !_bucle.value
        _reproductor.value!!.repeatMode =
            if (_bucle.value) Player.REPEAT_MODE_ONE else Player.REPEAT_MODE_OFF
    }

    fun clicAnterior(contexto: Context) {
        if (_random.value) {
            var temporal = (Math.random() * _canciones.value.size - 1).toInt()
            if (temporal >= _index.value) {
                temporal++
            }
            _index.value = temporal
        } else {
            _index.value--
            if (_index.value < 0) {
                _index.value = _canciones.value.lastIndex
            }
        }

        val mediaItem =
            MediaItem.fromUri(obtenerRuta(contexto, _canciones.value[_index.value].nombre))
        _reproductor.value!!.setMediaItem(mediaItem)
    }

    fun clicSiguiente(contexto: Context) {
        if (_random.value) {
            var temporal = (Math.random() * _canciones.value.size - 1).toInt()
            if (temporal >= _index.value) {
                temporal++
            }
            _index.value = temporal
        } else {
            _index.value++
            if (_index.value > _canciones.value.lastIndex) {
                _index.value = 0
            }
        }

        val mediaItem =
            MediaItem.fromUri(obtenerRuta(contexto, _canciones.value[_index.value].nombre))
        _reproductor.value!!.setMediaItem(mediaItem)
    }

    fun desplazarSlider(posicion : Int) {
        _reproductor.value!!.seekTo((posicion * 1000).toLong())
    }
}