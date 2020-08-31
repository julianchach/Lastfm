package com.example.fmmusic.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fmmusic.MainActivity
import com.example.fmmusic.R
import com.example.fmmusic.models.Artist
import com.example.fmmusic.models.Image
import com.example.fmmusic.utils.onClickListener

class ServiceAdapter(var context: Context, var mArtists: List<Artist>, var listener: onClickListener) : RecyclerView.Adapter<ServiceAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler, parent, false)

        return ViewHolder(layoutInflater)
    }

    override fun getItemCount(): Int {
        return mArtists.count()
    }

    override fun onBindViewHolder(holder: ServiceAdapter.ViewHolder, position: Int) {
        val item = mArtists[position]
        holder.bind(item)
        holder.cardViewItem.setOnClickListener {
            listener.onClickItem(item)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.name_item)
        val image = view.findViewById<ImageView>(R.id.image_item)
        val cardViewItem = view.findViewById<CardView>(R.id.cardViewItem)

        fun bind(item: Artist) {
            name.text = item.name
            image.loadImage(item.image)
        }

        private fun ImageView.loadImage(images: List<Image>) {
            val urlImage = images[1].text

            Glide.with(context)
                .load(urlImage)
                .into(image)

        }
    }
}