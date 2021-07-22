package com.viavarejo.desafio.android.tawane.hq.model

import com.google.gson.annotations.SerializedName
import com.viavarejo.desafio.android.tawane.hq.model.MarvelComics

data class MarvelComicsData(
    @SerializedName("offset") val offset: Int,
    @SerializedName("limit") val limit: Int,
    @SerializedName("total") val total: Int,
    @SerializedName("count") val count: Int,
    @SerializedName("results") val results: List<MarvelComics>
)