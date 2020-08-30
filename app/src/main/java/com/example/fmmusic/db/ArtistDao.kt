package com.example.fmmusic.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.fmmusic.models.Artist
import io.reactivex.Observable

@Dao
interface ArtistDao {

    @Insert
    fun insertArtists(artists: List<Artist>)

    @Query("SELECT * FROM artists")
    fun getArtists(): Observable<List<Artist>>

}