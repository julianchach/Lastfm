package com.example.fmmusic.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ArtistX(
    @SerializedName("topartists") val topArtists: Topartists
): Serializable