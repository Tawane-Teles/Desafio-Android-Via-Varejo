package com.viavarejo.desafio.android.tawane.hq.model

import com.google.gson.annotations.SerializedName
import com.viavarejo.desafio.android.tawane.hq.model.CharacterAllResponse

data class CharacterResponse(
    @SerializedName("data")
    var allResponse: CharacterAllResponse
)