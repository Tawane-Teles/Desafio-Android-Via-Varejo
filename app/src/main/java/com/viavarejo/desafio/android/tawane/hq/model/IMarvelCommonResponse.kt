package com.opah.desafio.tihasgdesafio.model

interface IMarvelCommonResponse {
    fun isSuccess(): Boolean

    fun getErrorMessage(): String
}