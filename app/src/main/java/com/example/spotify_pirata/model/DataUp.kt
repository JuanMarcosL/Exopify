package com.example.spotify_pirata.model

class DataUp {
    companion object {
        val cosasRandom = Playlist(
            "Cosas Random", "cosas_random",
            arrayListOf(
                Cancion("chop_suey", "Toxicity", "System of a Down"),
                Cancion("rise_up", "Single", "TheFatRat"),
                Cancion("respira_el_momento", "Multiviral", "Calle 13"),
                Cancion("pollito_pio", "Il pulcino Pio & friends", "Pulcino Pio"),
                Cancion(
                    "telefono_carpintero",
                    "Hablando con los animales",
                    "Las ardillitas de Lalo Guerrero"
                )
            )
        )
        val rapLatino = Playlist(
            "Rap Latino", "rap_latino",
            arrayListOf(
                Cancion("adentro", "Multiviral", "Calle 13"),
                Cancion("jeremias17_5", "Canserbero", "Canserbero"),
                Cancion("respira_el_momento", "Multiviral", "Calle 13"),
                Cancion("maquiavelico", "Canserbero", "Canserbero")
            )
        )
        val reggae = Playlist(
            "Reggae", "reggae",
            arrayListOf(
                Cancion("aire", "Espejitos", "Los cafres"),
                Cancion("binikini", "¿Quién trae las cornetas?", "Rawayana"),
                Cancion("red_red_wine", "Labour of Love", "UB40"),
                Cancion("parate_y_mira", "Pampas Reggae", "Los pericos")
            )
        )

        val toxicity = Disco(
            "Toxicity", "System of a Down", 2001,
            arrayListOf(
                Cancion("toxicity", "Toxicity", "System of a Down"),
                Cancion("chop_suey", "Toxicity", "System of a Down")
            )
        )

        val canserbero = Disco(
            "Canserbero", "Canserbero", 2012,
            arrayListOf(
                Cancion("jeremias17_5", "Canserbero", "Canserbero"),
                Cancion("maquiavelico", "Canserbero", "Canserbero")
            )
        )

        val multiviral = Disco(
            "Multiviral", "Calle 13", 2014,
            arrayListOf(
                Cancion("adentro", "Multiviral", "Calle 13"),
                Cancion("respira_el_momento", "Multiviral", "Calle 13")
            )
        )

        fun recopilarCanciones(): ArrayList<Cancion> {
            val lista = ArrayList<Cancion>()
            lista.addAll(cosasRandom.canciones)
            lista.addAll(rapLatino.canciones)
            lista.addAll(reggae.canciones)
            lista.addAll(toxicity.canciones)
            lista.addAll(canserbero.canciones)
            lista.addAll(multiviral.canciones)
            val definitiva = ArrayList<Cancion>()
            lista.forEach{
                if (!definitiva.contains(it)) {
                    definitiva.add(it)
                }
            }
            return definitiva
        }
    }
}