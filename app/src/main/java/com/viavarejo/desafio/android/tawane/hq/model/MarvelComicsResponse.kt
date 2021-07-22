package com.viavarejo.desafio.android.tawane.hq.model

import com.google.gson.annotations.SerializedName

data class MarvelComicsResponse(
    @SerializedName("data") val heroData: MarvelComicsData
) : MarvelCommonResponse()