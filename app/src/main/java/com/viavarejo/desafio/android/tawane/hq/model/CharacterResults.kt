package com.viavarejo.desafio.android.tawane.hq.model


import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterResults(
        @SerializedName("id")
        var characterId: Int,

        @Json(name = "name")
        var name: String,

        @Json(name = "description")
        var description: String,

        @Json(name = "thumbnail")
        var thumbnail: Thumbnail?,

        @Json(name = "resourceURI")
        val resourceURI: String
)