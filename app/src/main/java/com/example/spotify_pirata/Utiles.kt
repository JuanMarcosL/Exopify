package com.example.spotify_pirata

import android.content.Context

class Utiles {

    companion object {
        fun obtenerRuta(context: Context, nombreCancion: String): String {
            val resID = context.resources.getIdentifier(nombreCancion, "raw", context.packageName)
            return "android.resource://" + context.packageName + "/" + resID
        }
    }
}