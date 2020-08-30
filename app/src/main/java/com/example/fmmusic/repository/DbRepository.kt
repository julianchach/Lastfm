package com.example.fmmusic.repository

import com.example.fmmusic.db.ArtistDao
import com.example.fmmusic.models.Artist
import io.reactivex.Observable

class DbRepository(private val artistDao: ArtistDao) {

    fun addArtists(artists: List<Artist>) {
        artistDao.insertArtists(artists)
    }

    fun getArtists() : Observable<List<Artist>> {
        return artistDao.getArtists()
    }
}