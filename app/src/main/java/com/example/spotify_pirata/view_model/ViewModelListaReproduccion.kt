package com.example.spotify_pirata.view_model

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.spotify_pirata.model.Utiles.Companion.obtenerRuta
import com.example.spotify_pirata.model.DataUp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class ViewModelListaReproduccion : ViewModel() {
    private var _reproductor: MutableStateFlow<ExoPlayer?> = MutableStateFlow(null)
    val reproductor = _reproductor.asStateFlow()

    private var _isLightMode = MutableStateFlow(false)
    val isLightMode = _isLightMode.asStateFlow()

    private var _canciones = MutableStateFlow(DataUp.canciones)
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

    private var _searchBarAbierta = MutableStateFlow( false)
    val searchBarAbierta = _searchBarAbierta.asStateFlow()

    private var _reproduciendo = MutableStateFlow( false)
    val reproduciendo = _reproduciendo.asStateFlow()

    private var numeroClics = 0

    private fun reproducirLista(contexto: Context) {
        _reproductor.value!!.addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                if (playbackState == Player.STATE_READY) {
                    actualizarDuracion()
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

    fun actualizarDuracion() {
        val duracionMs = _reproductor.value!!.duration
        _duracionMinutos.value = TimeUnit.MILLISECONDS.toMinutes(duracionMs).toInt()
        _duracionSegundos.value = (TimeUnit.MILLISECONDS.toSeconds(duracionMs) % 60).toInt()
    }

    private fun actualizarPosicion() {
        viewModelScope.launch {
            while (true) {
                val posicionMs = _reproductor.value!!.currentPosition
                _posicionMinutos.value = TimeUnit.MILLISECONDS.toMinutes(posicionMs).toInt()
                _posicionSegundos.value = (TimeUnit.MILLISECONDS.toSeconds(posicionMs) % 60).toInt()

                delay(1000) //muy importante
            }
        }
    }

    fun crearReproductor(contexto: Context) {
        _reproductor.value = ExoPlayer.Builder(contexto).build()
        reproductor.value!!.prepare()
        actualizarCancion(contexto)
    }

    fun clicReproducir(contexto: Context) {
        if (numeroClics == 0) {
            viewModelScope.launch {
                reproducirLista(contexto)
                actualizarPosicion()
            }
        }
        _reproduciendo.value = !_reproduciendo.value
        _reproductor.value!!.playWhenReady = _reproduciendo.value
        numeroClics++
        actualizarDuracion()
    }

    fun clicAleatorio() {
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
        actualizarCancion(contexto)
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
        actualizarCancion(contexto)
    }

    fun desplazarSlider(posicion : Int) {
        _reproductor.value!!.seekTo((posicion * 1000).toLong())
    }

    fun seleccionarCancion(contexto : Context, nombre : String) {
        _canciones.value.forEach {
            if (it.nombre.replace('_', ' ') == nombre) {
                _index.value = _canciones.value.indexOf(it)
                val mediaItem =
                    MediaItem.fromUri(obtenerRuta(contexto, _canciones.value[_index.value].nombre))
                _reproductor.value!!.setMediaItem(mediaItem)
            }
        }
    }

    fun actualizarCancion(contexto: Context){
        if (numeroClics > 0) {
            _index.value--
        }
        _reproductor.value!!.clearMediaItems()
        val mediaItem = MediaItem.fromUri(obtenerRuta(contexto, _canciones.value[index.value].nombre))
        _reproductor.value!!.setMediaItem(mediaItem)
    }

    fun cambiarModo() {
        _isLightMode.value = !_isLightMode.value
    }

    fun cambiarSearchBar() {
        _searchBarAbierta.value = !_searchBarAbierta.value
    }
}