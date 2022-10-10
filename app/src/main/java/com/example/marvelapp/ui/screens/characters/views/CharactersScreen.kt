package com.example.marvelapp.ui.screens.characters.views

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.marvelapp.ui.app.MarvelAppScreen
import com.example.marvelapp.ui.common.ErrorScreen
import com.example.marvelapp.ui.common.ItemRow
import com.example.marvelapp.ui.common.LoadingScreen
import com.example.marvelapp.ui.screens.characters.viewmodel.CharactersViewModel

@Composable
fun CharactersScreenState(charactersViewModel: CharactersViewModel = viewModel()) {
    val state by charactersViewModel.state.collectAsState()
    charactersViewModel.fetchCharacters()

    when (state) {
        is CharactersViewModel.UIState.Loading -> {
            LoadingScreen()
        }
        is CharactersViewModel.UIState.Success<*> -> {
            CharactersScreen((state as CharactersViewModel.UIState.Success<*>).data)
        }
        is CharactersViewModel.UIState.Error -> {
            ErrorScreen(message = (state as CharactersViewModel.UIState.Error).message)
        }
    }
}

@Composable
fun <T> CharactersScreen(data: List<T>) {
    val state = rememberLazyListState()
    MarvelAppScreen {
        Scaffold {
            LazyColumn(state = state) {
                items(data) { item ->
                    ItemRow(data = item)
                }
            }
        }
    }
}