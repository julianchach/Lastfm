package com.example.fmmusic.repository

import com.example.fmmusic.api.ApiService
import com.example.fmmusic.models.ArtistX
import io.reactivex.Observable
import retrofit2.Response

class Repository(private val apiService: ApiService) {

    fun getArtistsRetrofit(): Observable<Response<ArtistX>> {
        return apiService.getArtistsApi()
    }

}