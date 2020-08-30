package com.example.fmmusic.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.fmmusic.models.Artist
import com.example.fmmusic.utils.Converters

@Database(entities = [Artist::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ArtistRoomDatabase: RoomDatabase() {

    abstract fun artistDao(): ArtistDao
}