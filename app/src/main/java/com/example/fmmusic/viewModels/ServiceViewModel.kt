package com.example.fmmusic.viewModels

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fmmusic.models.*
import com.example.fmmusic.repository.DbRepository
import com.example.fmmusic.repository.Repository
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ServiceViewModel(var dbRepository: DbRepository, var repository: Repository) : ViewModel() {

    private val disposable = CompositeDisposable()
    private val responseArtistApi = MutableLiveData<ApiServiceResponse<ArtistX>>()
    private val responseArtistDb = MutableLiveData<List<Artist>>()

    fun apiArtistResponse(): MutableLiveData<ApiServiceResponse<ArtistX>> {
        return responseArtistApi
    }

    fun dbArtistsResponse(): MutableLiveData<List<Artist>>? {
        return responseArtistDb
    }


    fun getArtistsApi() {
        disposable.add(repository.getArtistsRetrofit()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                responseArtistApi.setValue(
                    ApiServiceResponse(
                        State.LOADING,
                        null,
                        null
                    )
                )
            }
            .subscribe(
                { result ->
                    responseArtistApi.setValue(
                        ApiServiceResponse(
                            State.SUCCESS,
                            result,
                            null
                        )
                    )
                },
                { throwable ->
                    responseArtistApi.setValue(
                        ApiServiceResponse(
                            State.ERROR,
                            null,
                            throwable
                        )
                    )
                }
            ))

    }

    @SuppressLint("CheckResult")
    fun saveArtist(artistX: ApiServiceResponse<ArtistX>) {
        val mArtists: MutableList<Artist> = mutableListOf()
        val artist = artistX.response!!.body()!!.topArtists.artist
        artist.forEach {
            mArtists.add(Artist(it.name, it.listeners, it.mbid, it.url, it.streamable, it.image))
        }
        Completable.fromAction {
            dbRepository.addArtists(mArtists)
        }.subscribeOn(Schedulers.io())
            .subscribe({
                Log.d("db", "Msg: list insertion was successful")
            }, {
                Log.d("db", "Msg: list insertion wasn't successful")
                it.printStackTrace()
            })
    }

    fun getArtists() {
        disposable.add(dbRepository.getArtists()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { responseArtistDb.setValue(ArrayList())}
            .subscribe(
                {result -> responseArtistDb.setValue(result) },
                {responseArtistDb.setValue(null)}
            ))

    }
}