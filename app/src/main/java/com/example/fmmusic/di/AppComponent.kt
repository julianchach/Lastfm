package com.example.fmmusic.di

import android.content.Context
import com.example.fmmusic.view.ArtistServiceActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {
    fun inject(context: Context)
    fun inject(artistServiceActivity: ArtistServiceActivity)
}