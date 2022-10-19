package com.example.marvelapp.ui.screens.characters.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.marvelapp.R
import com.example.marvelapp.data.remote.model.ApiCharacter
import com.example.marvelapp.ui.app.MarvelAppScreen
import com.example.marvelapp.ui.common.ErrorScreen
import com.example.marvelapp.ui.common.ItemRow
import com.example.marvelapp.ui.common.LoadingScreen
import com.example.marvelapp.ui.common.MainTopBar
import com.example.marvelapp.ui.screens.characters.viewmodel.CharactersViewModel

@ExperimentalFoundationApi
@Composable
fun CharactersScreenState(
    onBackButton: () -> Unit,
    navigateToComics: (Int) -> Unit,
    charactersViewModel: CharactersViewModel = hiltViewModel()
) {
    val state by charactersViewModel.state.collectAsState()
    charactersViewModel.fetchCharacters()

    when (state) {
        is CharactersViewModel.UIState.Loading -> {
            LoadingScreen()
        }
        is CharactersViewModel.UIState.Success<*> -> {
            CharactersScreen(
                onBackButton = onBackButton,
                navigateToComics = navigateToComics,
                data = (state as CharactersViewModel.UIState.Success<*>).data
            )
        }
        is CharactersViewModel.UIState.Error -> {
            ErrorScreen(message = (state as CharactersViewModel.UIState.Error).message)
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun <T> CharactersScreen(
    onBackButton: () -> Unit,
    navigateToComics: (Int) -> Unit,
    data: List<T>
) {
    val state = rememberLazyListState()
    MarvelAppScreen {
        Scaffold(
            topBar = {
                MainTopBar(
                    barTitle = stringResource(id = R.string.marvel_characters_TEXT),
                    onBackClick = onBackButton
                )
            }
        ) {
            LazyVerticalGrid(
                state = state,
                cells = GridCells.Adaptive(180.dp),
                contentPadding = PaddingValues(4.dp)
            ) {
                items(data) { item ->
                    ItemRow(
                        data = item,
                        onItemClick = {
                            navigateToComics((item as ApiCharacter).id)
                        }
                    )
                }
            }
        }
    }
}