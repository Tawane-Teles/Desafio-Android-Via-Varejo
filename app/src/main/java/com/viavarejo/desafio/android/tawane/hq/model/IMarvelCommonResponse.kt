package com.viavarejo.desafio.android.tawane.hq.model

interface IMarvelCommonResponse {
    fun isSuccess(): Boolean

    fun getErrorMessage(): String
}