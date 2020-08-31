package com.example.fmmusic.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.fmmusic.R
import com.example.fmmusic.models.Artist


private const val ARG_PARAM1 = "artist"

class DetailArtistFragment : DialogFragment() {
    private lateinit var artist: Artist

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            artist = it.getSerializable(ARG_PARAM1) as Artist
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_detail_artist, container, false)
        val name = view.findViewById<TextView>(R.id.name)
        val listeners = view.findViewById<TextView>(R.id.listeners)
        val mbid = view.findViewById<TextView>(R.id.mbid)
        val url = view.findViewById<TextView>(R.id.url)
        val streamable = view.findViewById<TextView>(R.id.streamable)
        val image = view.findViewById<ImageView>(R.id.image)

        image.loadUrl(artist.image[2].text)
        name.text = artist.name
        listeners.text = artist.listeners
        mbid.text = artist.mbid
        url.text = artist.url
        streamable.text = artist.streamable

        return view
    }


    private fun ImageView.loadUrl(url: String) {
        val options = RequestOptions()
        Glide.with(context)
            .load(url)
            .apply(
                options.transforms(
                    CenterCrop(),
                    RoundedCorners(300)
                )
            )
            .into(this)
    }

    companion object {

        @JvmStatic
        fun newInstance(artist: Artist) =
            DetailArtistFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, artist)
                }
            }
    }
}