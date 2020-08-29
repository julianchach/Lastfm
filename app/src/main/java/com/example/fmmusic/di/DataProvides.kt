package com.example.fmmusic.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.fmmusic.api.ApiService
import com.example.fmmusic.db.ArtistDao
import com.example.fmmusic.db.ArtistRoomDatabase
import com.example.fmmusic.repository.DbRepository
import com.example.fmmusic.repository.Repository
import com.example.fmmusic.viewModels.ViewModelFactory
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class DataProvides {

    @Singleton
    @Provides
    fun dataBaseInstance(context: Context): ArtistRoomDatabase {
        return Room.databaseBuilder (
            context,
            ArtistRoomDatabase::class.java,"artist-database"
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideShowDao(artistDB: ArtistRoomDatabase): ArtistDao {
        return artistDB.artistDao()
    }

    @Provides
    @Singleton
    fun instanceGson(): Gson {
        val builder =
            GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return builder.setLenient().create()
    }

    @Provides
    @Singleton
    fun retrofitObject(gson: Gson, okHttpClient: OkHttpClient): Retrofit {

        val baseUrl = "https://ws.audioscrobbler.com/2.0/"

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun getApiCallInterface(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun getRequestHeader(): OkHttpClient {

        val httpClient = OkHttpClient.Builder()

        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .build()
            chain.proceed(request)
        }
            .connectTimeout(100, TimeUnit.SECONDS)
            .writeTimeout(100, TimeUnit.SECONDS)
            .readTimeout(300, TimeUnit.SECONDS)

        return httpClient.build()
    }

    @Provides
    @Singleton
    fun provideViewModelFactory(dbRepository: DbRepository, repository: Repository): ViewModelProvider.Factory {
        return ViewModelFactory(dbRepository, repository)
    }

    @Provides
    @Singleton
    fun getRepository(apiService: ApiService): Repository {
        return Repository(apiService)
    }

    @Provides
    @Singleton
    fun getDbRepository(artistDao: ArtistDao): DbRepository {
        return DbRepository(artistDao)
    }


}