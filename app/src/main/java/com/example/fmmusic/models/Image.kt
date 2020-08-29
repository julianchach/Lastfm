package com.example.fmmusic.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Image(
    @SerializedName("#text") val text: String,
    val size: String
): Serializable