package com.example.spotify_pirata.gui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.SearchBar
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.res.painterResource
import com.example.spotify_pirata.R
import com.example.spotify_pirata.model.DataUp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(isLigthMode: Boolean, onSearchSelected: (String) -> Unit) {
    val songs = DataUp.recopilarCanciones()
    val songsName: ArrayList<String> = ArrayList()
    songs.forEach { songsName.add(it.nombre.replace('_', ' ')) }
    var query by remember { mutableStateOf("") }
    var isActive by remember { mutableStateOf(true) }
    var filteredSongs: List<String> by remember { mutableStateOf(songsName) }

    SearchBar(
        query = query,
        onQueryChange = { newQuery ->
            query = newQuery
            filteredSongs = songsName.filter { it.contains(newQuery, ignoreCase = true) }
        },
        onSearch = { onSearchSelected(query) },
        active = isActive,
        onActiveChange = { },
        placeholder = { Text("¿Cuál es la canción?") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search, contentDescription = "Icono para buscar"
            )
        },
        trailingIcon = {
            IconButton(onClick = {
                query = ""
                filteredSongs = songsName
                onSearchSelected("")
            }) {
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = "Icono para borrar lo escrito"
                )
            }
        },
        modifier = Modifier.fillMaxWidth(0.7f)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(filteredSongs) { cancion ->
                TextButton(
                    onClick = {
                        query = cancion
                        onSearchSelected(cancion)
                    }, modifier = Modifier
                        .fillMaxWidth()
                        .background(color = if (isLigthMode) Color.White else Color.DarkGray)
                        .border(
                            width = .5.dp,
                            color = if (isLigthMode) Color.White else Color.DarkGray,
                        )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.music_note_fill0_wght400_grad0_opsz24),
                            contentDescription = "Icono de nota musical",
                            modifier = Modifier.size(24.dp),
                            tint = Color.Green
                        )
                        Text(
                            text = cancion,
                            fontSize = 26.sp,
                            color = Color.Green,
                            modifier = Modifier.padding(start = 10.dp)
                        )
                    }
                }
            }
        }
    }
}