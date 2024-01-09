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
                ),
            )
        )
        val rapLatino = Playlist(
            "Rap Latino", "rap_latino",
            arrayListOf(
                Cancion("chop_suey", "Toxicity", "System of a Down"),
                Cancion("rise_up", "Single", "TheFatRat"),
                Cancion("respira_el_momento", "Multiviral", "Calle 13"),
                Cancion("pollito_pio", "Il pulcino Pio & friends", "Pulcino Pio"),
                Cancion(
                    "telefono_carpintero",
                    "Hablando con los animales",
                    "Las ardillitas de Lalo Guerrero"
                ),
            )
        )
        val reggae = Playlist(
            "Reggae", "reggae",
            arrayListOf(
                Cancion("chop_suey", "Toxicity", "System of a Down"),
                Cancion("rise_up", "Single", "TheFatRat"),
                Cancion("respira_el_momento", "Multiviral", "Calle 13"),
                Cancion("pollito_pio", "Il pulcino Pio & friends", "Pulcino Pio"),
                Cancion(
                    "telefono_carpintero",
                    "Hablando con los animales",
                    "Las ardillitas de Lalo Guerrero"
                ),
            )
        )

        val toxicity = Disco(
            "Toxicity","System of a Down",2001,
            arrayListOf(
                Cancion("chop_suey", "Toxicity", "System of a Down"),

            )
        )

        val canserbero = Disco(
            "Canserbero","Canserbero",2012,
            arrayListOf(
                Cancion("chop_suey", "Toxicity", "System of a Down"),

                )
        )

        val multiviral = Disco(
            "Multiviral","Calle 13",2014,
            arrayListOf(
                Cancion("respira_el_momento", "Multiviral", "Calle 13"),

                )
        )

        fun recopilarCanciones() : List<Cancion> {
            val lista : ArrayList<Cancion> = cosasRandom.canciones
            lista.addAll(rapLatino.canciones)
            lista.addAll(reggae.canciones)
            lista.addAll(toxicity.canciones)
            lista.addAll(canserbero.canciones)
            lista.addAll(multiviral.canciones)
            return lista
        }
    }
}