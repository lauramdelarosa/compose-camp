package com.delarosa.composeapp.album.domain

import com.delarosa.composeapp.song.domain.Song

data class Album(val image: Int, val name: String, val artist: String, val song: Song)
