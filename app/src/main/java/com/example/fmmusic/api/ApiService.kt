package com.example.fmmusic.api

import com.example.fmmusic.models.Artist
import com.example.fmmusic.models.ArtistX
import com.example.fmmusic.models.Topartists
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("?method=geo.gettopartists&country=spain&api_key=829751643419a7128b7ada50de590067&format=json")
    fun getArtistsApi(): Observable<Response<ArtistX>>
}