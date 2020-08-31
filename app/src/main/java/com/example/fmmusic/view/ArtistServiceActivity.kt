package com.example.fmmusic.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.fmmusic.App
import com.example.fmmusic.MainActivity
import com.example.fmmusic.R
import com.example.fmmusic.adapter.ServiceAdapter
import com.example.fmmusic.models.ApiServiceResponse
import com.example.fmmusic.models.Artist
import com.example.fmmusic.models.ArtistX
import com.example.fmmusic.models.State
import com.example.fmmusic.utils.onClickListener
import com.example.fmmusic.viewModels.ServiceViewModel
import com.example.fmmusic.viewModels.ViewModelFactory
import kotlinx.android.synthetic.main.activity_artist_service.*
import javax.inject.Inject

class ArtistServiceActivity : AppCompatActivity(), onClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var serviceViewModel: ServiceViewModel
    private lateinit var mServiceAdapter: ServiceAdapter
    private lateinit var artistsRecyclerView: RecyclerView
    private lateinit var mArtists: List<Artist>

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist_service)

        serviceViewModel = ViewModelProvider(this, viewModelFactory).get(ServiceViewModel::class.java)

        serviceViewModel.getArtists()

        serviceViewModel.apiArtistResponse().observe(this,
            Observer { artistX ->  saveArtists(artistX)})

        serviceViewModel.dbArtistsResponse()?.observe(this,
            Observer { mArtists -> showArtists(mArtists) })

    }


    fun saveArtists(artistsX: ApiServiceResponse<ArtistX>) {

        when (artistsX.state) {

            State.LOADING -> {
                showProgress(true)
            }
            State.SUCCESS -> {
                showProgress(false)
                serviceViewModel.saveArtist(artistsX)
            }
            State.ERROR -> {
                showProgress(false)
                Log.d("Error", "${artistsX.error}")

            }
        }

    }

    fun showArtists(artistsList: List<Artist>?) {
        if (!artistsList.isNullOrEmpty()) {
            mArtists = artistsList!!
            artistsRecyclerView = artists_recycler
            mServiceAdapter = ServiceAdapter(this, mArtists, this)
            artistsRecyclerView.adapter = mServiceAdapter
        } else {
            serviceViewModel.getArtistsApi()

        }

    }

    override fun onClickItem(artists: Artist) {
        val detailArtistFragment = DetailArtistFragment.newInstance(artists)
        detailArtistFragment.show(supportFragmentManager, "detailArtist")
        detailArtistFragment.isCancelable = true
    }

    private fun showProgress(show: Boolean) {
        val shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime)
        progressBar.visibility = if (show) View.VISIBLE else View.GONE
        progressBar.animate().setDuration(shortAnimTime.toLong()).alpha(
            (if (show) 1 else 0).toFloat()
        ).setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                progressBar.visibility = if (show) View.VISIBLE else View.GONE
            }
        })
    }
}