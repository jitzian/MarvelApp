package com.example.marvelapp.ui.screens.characters.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelapp.R
import com.example.marvelapp.constants.GlobalConstants
import com.example.marvelapp.data.remote.model.ApiCharacter
import com.example.marvelapp.data.remote.model.ApiResponse
import com.example.marvelapp.domain.repository.characters.CharactersRepository
import com.example.marvelapp.utils.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val charactersRepository: CharactersRepository,
    private val appContext: Application
) : ViewModel() {

    private val _state = MutableStateFlow<UIState>(UIState.Loading)
    val state: StateFlow<UIState>
        get() = _state.asStateFlow()

    fun fetchCharacters() = viewModelScope.launch {
        try {
            withTimeout(GlobalConstants.TIME_OUT) {
                withContext(Dispatchers.IO) {
                    val result: ApiResponse<ApiCharacter>
                    if (state.value == UIState.Loading) {
                        //TODO: Move offset and limit params to constants..
                        result = charactersRepository.fetchCharacters(0, 100)
                        Log.e(
                            this@CharactersViewModel.TAG(),
                            "fetchCharacters: -->>  ${result.data.results.size}"
                        )
                        if (result.data.results.isEmpty()) {
                            _state.value = UIState.Error(
                                message = appContext.getString(R.string.thereIsNoDataAvailable_TEXT)
                            )
                        } else {
                            _state.value = UIState.Success(data = result.data.results)
                        }
                    }
                }
            }
        } catch (tce: TimeoutCancellationException) {
            Log.e(this@CharactersViewModel.TAG(), "fetchCharacters: ${tce.message}")
            _state.value = UIState.Error(
                message = tce.message ?: appContext.getString(R.string.timeOutError_TEXT)
            )
        } catch (e: Exception) {
            Log.e(this@CharactersViewModel.TAG(), "fetchCharacters: ${e.message}")
            _state.value = UIState.Error(
                message = e.message ?: appContext.getString(R.string.genericError_TEXT)
            )
        }
    }

    sealed class UIState {
        object Loading : UIState()
        class Success<T>(val data: List<T>) : UIState()
        class Error(val message: String) : UIState()
    }
}