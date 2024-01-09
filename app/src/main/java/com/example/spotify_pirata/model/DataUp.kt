package com.example.spotify_pirata.model

class DataUp {
    companion object {
        val cosasRandom = Playlist(
            "Cosas Random", "cosas_random",
            listOf(
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
            listOf(
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
            listOf(
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
            listOf(
                Cancion("chop_suey", "Toxicity", "System of a Down"),

            )
        )

        val cancerbero = Disco(
            "Cancerbero","Cancerbero",2012,
            listOf(
                Cancion("chop_suey", "Toxicity", "System of a Down"),

                )
        )

        val multiviral = Disco(
            "Multiviral","Calle 13",2014,
            listOf(
                Cancion("respira_el_momento", "Multiviral", "Calle 13"),

                )
        )
    }
}