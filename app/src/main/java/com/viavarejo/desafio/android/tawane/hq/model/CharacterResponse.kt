package com.opah.desafio.tihasgdesafio.model

import com.google.gson.annotations.SerializedName
import com.opah.desafio.tihasgdesafio.model.CharacterAllResponse

data class CharacterResponse(
    @SerializedName("data")
    var allResponse: CharacterAllResponse
)