package com.viavarejo.desafio.android.tawane.hq.ui.home.adpater

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.viavarejo.desafio.android.tawane.hq.R
import com.viavarejo.desafio.android.tawane.hq.model.CharacterResults


class MarvelRecyclerAdapter(private val getCharacter: (CharacterResults) -> Unit) : RecyclerView.Adapter<MarvelRecyclerAdapter.ViewHolder>() {

    var marvelList = listOf<CharacterResults>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_characters, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return marvelList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val marvel = marvelList[position]
        holder.bind(marvel, getCharacter)
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(marvel: CharacterResults, getCharacter: (CharacterResults) -> Unit) {
            val name = itemView.findViewById<TextView>(R.id.textView)
            val image = itemView.findViewById<ImageView>(R.id.imageView)
            val click = itemView.findViewById<ConstraintLayout>(R.id.idClick)

            name.text = marvel.name

            Glide.with(image.context).load(marvel.thumbnail!!.getCompletePath()).into(image)

            click.setOnClickListener {
                getCharacter(marvel)
            }
        }
    }


}