package com.opah.desafio.tihasgdesafio.model

import com.google.gson.annotations.SerializedName
import com.opah.desafio.felipe.models.MarvelComicsData

data class MarvelComicsResponse(
    @SerializedName("data") val heroData: MarvelComicsData
) : MarvelCommonResponse()