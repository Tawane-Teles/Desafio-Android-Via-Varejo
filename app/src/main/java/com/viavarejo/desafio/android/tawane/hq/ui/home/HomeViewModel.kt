package com.viavarejo.desafio.android.tawane.hq.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.viavarejo.desafio.android.tawane.hq.model.CharacterResponse
import com.viavarejo.desafio.android.tawane.hq.model.CharacterResults
import com.viavarejo.desafio.android.tawane.hq.repository.CharacterRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel(private val characterRepository: CharacterRepository) : ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    private val _state = MutableLiveData<ScreenState>()
    val state: LiveData<ScreenState>
        get() = _state

    fun takeIntention(intention: Intention) {
        when (intention) {
            is Intention.LoadInitialData -> {
                getCharacters()
            }
        }
    }

     fun getCharacters() {
        _state.postValue(ScreenState.Loading)

        launch {
            try {
                if (characterRepository.getCharacter().isSuccessful){
                    _state.postValue(
                        ScreenState.ApiSucesso(
                            characterRepository.getCharacter().body()!!
                        )
                    )
                }

            }catch (e: Exception){
                _state.postValue(ScreenState.ApiError(e.message.toString()))
            }

        }

    }

    fun savePosition(marvel: CharacterResults){
         characterRepository.savePositionClick(marvel)
        _state.postValue(ScreenState.NavigateToDetails)
    }

    sealed class ScreenState {
        object Loading : ScreenState()
        object NavigateToDetails: ScreenState()

        data class ApiError(val message: String) : ScreenState()
        data class ApiSucesso(val value: CharacterResponse) : ScreenState()
    }

    sealed class Intention {
        object LoadInitialData : Intention()
    }

    class HomeIntention(private val emit: (Intention) -> Unit) {
        fun loadInitialData() = emit(Intention.LoadInitialData)
    }

}