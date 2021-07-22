package com.viavarejo.desafio.android.tawane.hq.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.viavarejo.desafio.android.tawane.hq.model.CharacterResults
import com.viavarejo.desafio.android.tawane.hq.model.MarvelComicsResponse
import com.viavarejo.desafio.android.tawane.hq.repository.CharacterRepository

class CharacterDetailsViewModel(private val repository: CharacterRepository) : ViewModel() {

    private val _state = MutableLiveData<ScreenState>()
    val state: LiveData<ScreenState>
        get() = _state

    fun takeIntention(intention: Intention) {
        when (intention) {
            is Intention.LoadInitViewModel -> {
                getPosition()
            }
            is Intention.NavigateToHome -> {
                navigateToHome()
            }
        }
    }

    fun getPosition() {
        _state.postValue(
            ScreenState.GetPosition(
                repository.getPositionClick()
                    ?: CharacterResults(
                        characterId = 11,
                        name = "Hero",
                        description = "Teste",
                        thumbnail = null,
                        resourceURI = ""
                    ), repository.getDataHQ()
            )
        )


    }

    fun navigateToHome() {
        _state.postValue(ScreenState.NavigateToHome)
    }

    sealed class ScreenState {
        data class GetPosition(val value: CharacterResults, val hqs: MarvelComicsResponse?) :
            ScreenState()

        object NavigateToHome : ScreenState()
    }

    sealed class Intention {
        object LoadInitViewModel : Intention()
        object NavigateToHome : Intention()
    }

    class DetailsIntention(private val emit: (Intention) -> Unit) {
        fun loadInitViewModel() = emit(Intention.LoadInitViewModel)
        fun navigateToHome() = emit(Intention.NavigateToHome)
    }

}