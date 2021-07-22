package com.opah.desafio.felipe.models

import com.google.gson.annotations.SerializedName
import com.opah.desafio.tihasgdesafio.model.MarvelComics

data class MarvelComicsData(
    @SerializedName("offset") val offset: Int,
    @SerializedName("limit") val limit: Int,
    @SerializedName("total") val total: Int,
    @SerializedName("count") val count: Int,
    @SerializedName("results") val results: List<MarvelComics>
)