package com.example.fmmusic.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.fmmusic.App
import com.example.fmmusic.R
import com.example.fmmusic.models.ApiServiceResponse
import com.example.fmmusic.models.Artist
import com.example.fmmusic.models.ArtistX
import com.example.fmmusic.models.State
import com.example.fmmusic.viewModels.ServiceViewModel
import com.example.fmmusic.viewModels.ViewModelFactory
import javax.inject.Inject

class ArtistServiceActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var serviceViewModel: ServiceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist_service)

        serviceViewModel = ViewModelProvider(this, viewModelFactory).get(ServiceViewModel::class.java)


        serviceViewModel.apiArtistResponse().observe(this,
        Observer { artistX ->  saveArtists(artistX)})

        serviceViewModel.dbArtistsResponse().observe(this,
        Observer { mArtists -> showArtists(mArtists) })

        serviceViewModel.getArtistsApi()



    }

    fun saveArtists(artistsX: ApiServiceResponse<ArtistX>) {

        when (artistsX.state) {

            State.LOADING -> {
                //showProgress(true)
            }
            State.SUCCESS -> {
                serviceViewModel.saveArtist(artistsX)
                serviceViewModel.getArtists()
            }
            State.ERROR -> {
                //showProgress(false)
                Log.d("Error", "${artistsX.error}")

            }
        }

    }

    fun showArtists(mArtists: List<Artist>) {

    }
}