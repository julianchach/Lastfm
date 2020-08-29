package com.example.fmmusic.models

import java.io.Serializable

data class Attr(
    val country: String,
    val page: String,
    val perPage: String,
    val totalPages: String,
    val total: String
): Serializable