package com.example.fmmusic.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Topartists(
    val artist: List<Artist>,
    @SerializedName("@attr") val attr: Attr
): Serializable