package com.example.marvelapp.ui.screens.characters.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelapp.constants.GlobalConstants
import com.example.marvelapp.domain.repository.characters.CharactersRepository
import com.example.marvelapp.utils.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val charactersRepository: CharactersRepository
) : ViewModel() {

    fun fetchCharacters() = viewModelScope.launch {
        try {
            withTimeout(GlobalConstants.TIME_OUT) {
                withContext(Dispatchers.IO) {
                    val result = charactersRepository.fetchCharacters(0, 100)
                    Log.e(this.TAG(), "fetchCharacters: -->>  ${result.data.results.size}")
                }
            }
        } catch (tce: TimeoutCancellationException) {
            Log.e(this.TAG(), "fetchCharacters: ${tce.message}")
        } catch (e: Exception) {
            Log.e(this.TAG(), "fetchCharacters: ${e.message}")
        }
    }

    sealed class UIState {
        object Loading : UIState()
        class Success<T>(data: T) : UIState()
        class Error(val message: String) : UIState()
    }
}