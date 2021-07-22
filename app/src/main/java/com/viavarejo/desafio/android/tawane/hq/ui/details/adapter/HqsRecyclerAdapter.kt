package com.viavarejo.desafio.android.tawane.hq.ui.details.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.viavarejo.desafio.android.tawane.hq.R
import com.viavarejo.desafio.android.tawane.hq.model.MarvelComics


class HqsRecyclerAdapter : RecyclerView.Adapter<HqsRecyclerAdapter.ViewHolder>() {

    var marvelList = listOf<MarvelComics>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_hqs, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return marvelList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val marvel = marvelList[position]
        holder.bind(marvel)
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(marvel: MarvelComics) {
            val name = itemView.findViewById<TextView>(R.id.textView)
            val image = itemView.findViewById<ImageView>(R.id.imageView)

            name.text = marvel.title

            Glide.with(image.context).load(marvel.thumbnail?.getCompletePath()).into(image)
        }
    }


}