package com.example.fmmusic.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.fmmusic.models.Artist

@Database(entities = [Artist::class], version = 1, exportSchema = false)
abstract class ArtistRoomDatabase: RoomDatabase() {

    abstract fun artistDao(): ArtistDao
}