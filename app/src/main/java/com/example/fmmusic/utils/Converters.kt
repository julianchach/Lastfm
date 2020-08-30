package com.example.fmmusic.utils

import androidx.room.TypeConverter
import com.example.fmmusic.models.Image
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken


class Converters {
    @TypeConverter
    fun fromString(value: String): List<Image?>? {
        val listType = object : TypeToken<List<Image?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<Image>): String? {
        val gson = Gson()
        return gson.toJson(list).toString()
    }
}