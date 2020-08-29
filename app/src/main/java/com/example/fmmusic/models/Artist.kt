package com.example.fmmusic.models

import java.io.Serializable

data class Artist(
    val name: String,
    val listeners: String,
    val mbid: String,
    val url: String,
    val streamable: String,
    val image: List<Image>
): Serializable