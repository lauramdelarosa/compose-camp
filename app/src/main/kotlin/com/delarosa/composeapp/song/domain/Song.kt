package com.delarosa.composeapp.song.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Song(val image: Int, val name: String, val artist: String) : Parcelable
