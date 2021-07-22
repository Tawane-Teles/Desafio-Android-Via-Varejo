package com.viavarejo.desafio.android.tawane.hq.repository

import com.orhanobut.hawk.Hawk
import com.viavarejo.desafio.android.tawane.hq.model.CharacterResults
import com.viavarejo.desafio.android.tawane.hq.model.MarvelComicsResponse
import com.viavarejo.desafio.android.tawane.hq.network.ApiService

class CharacterRepository(private val apiService: ApiService) {

    suspend fun getCharacter() = apiService.getCharacters().await()

    suspend fun findComics(characterId: Int) = apiService.findComics(characterId).await()

    fun savePositionClick (marvel: CharacterResults){
        Hawk.put("marvel", marvel)
    }

    fun getPositionClick (): CharacterResults? {
        return Hawk.get("marvel")
    }


    fun saveHQ(dataHQ: MarvelComicsResponse) {
        Hawk.put(DATAHQ, dataHQ)
    }

    fun getDataHQ(): MarvelComicsResponse? {
        return Hawk.get(DATAHQ)
    }

    companion object {
        const val CHARACTERRESULTS = "characterResults"
        const val DATAHQ = "dataHQ"
    }
}