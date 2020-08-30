package com.example.fmmusic.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fmmusic.R
import com.example.fmmusic.models.Artist

class ServiceAdapter(var context: Context, var mArtists: List<Artist>) : RecyclerView.Adapter<ServiceAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler, parent, false)

        return ViewHolder(layoutInflater)

    }

    override fun getItemCount(): Int {
        return mArtists.count()
    }

    override fun onBindViewHolder(holder: ServiceAdapter.ViewHolder, position: Int) {

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {


    }
}