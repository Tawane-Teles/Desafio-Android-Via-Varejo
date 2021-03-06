package com.viavarejo.desafio.android.tawane.hq.model

import com.google.gson.annotations.SerializedName

data class MarvelComics(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("thumbnail") val thumbnail: Thumbnail?,
    @SerializedName("prices") var price: ArrayList<Prices>?
)