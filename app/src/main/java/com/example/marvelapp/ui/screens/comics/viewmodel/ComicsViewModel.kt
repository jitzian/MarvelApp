package com.example.marvelapp.ui.screens.comics.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelapp.R
import com.example.marvelapp.constants.GlobalConstants
import com.example.marvelapp.data.remote.model.ApiComic
import com.example.marvelapp.data.remote.model.ApiResponse
import com.example.marvelapp.domain.repository.comics.ComicsRepository
import com.example.marvelapp.utils.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ComicsViewModel @Inject constructor(
    private val comicsRepository: ComicsRepository,
    private val appContext: Application
) : ViewModel() {

    private val _state = MutableStateFlow<UIState>(UIState.Loading)
    val state: StateFlow<UIState>
        get() = _state.asStateFlow()

    fun fetchComics(characterId: Int) = viewModelScope.launch {
        try {
            withTimeout(GlobalConstants.TIME_OUT) {
                withContext(Dispatchers.IO) {
                    val result: ApiResponse<ApiComic>
                    if (_state.value == UIState.Loading) {
                        result = comicsRepository.fetchComics(characterId)
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
            Log.e(this@ComicsViewModel.TAG(), "fetchComics: ${tce.message}")
            _state.value = UIState.Error(message = appContext.getString(R.string.timeOutError_TEXT))
        } catch (e: Exception) {
            Log.e(this@ComicsViewModel.TAG(), "fetchComics: ${e.message}")
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