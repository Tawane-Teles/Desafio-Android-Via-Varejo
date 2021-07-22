package com.opah.desafio.tihasgdesafio.model

import com.google.gson.annotations.SerializedName
import com.viavarejo.desafio.android.tawane.hq.model.Prices

data class MarvelComics(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("thumbnail") val thumbnail: Thumbnail,
    @SerializedName("prices") var price: ArrayList<Prices>
)