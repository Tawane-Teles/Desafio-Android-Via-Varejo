package com.viavarejo.desafio.android.tawane.hq.model

import com.squareup.moshi.Json
import com.viavarejo.desafio.android.tawane.hq.model.CharacterResults

data class CharacterAllResponse(
    @Json(name = "offset")
    var offset: Int,

    @Json(name = "limit")
    var limit: Int,

    @Json(name = "total")
    var total: Int,

    @Json(name = "count")
    var count: Int,

    @Json(name = "results")
    var results: List<CharacterResults>
)