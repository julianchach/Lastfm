package com.example.fmmusic.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "ArtistDatabase")
data class Artist(
    @PrimaryKey val name: String,
    val listeners: String,
    val mbid: String,
    val url: String,
    val streamable: String,
    val image: String//List<Image>
): Serializable