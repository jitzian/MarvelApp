package com.example.marvelapp.ui.screens.comics.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.marvelapp.R
import com.example.marvelapp.data.remote.model.ApiReference
import com.example.marvelapp.ui.app.MarvelAppScreen
import com.example.marvelapp.ui.common.ErrorScreen
import com.example.marvelapp.ui.common.LoadingScreen
import com.example.marvelapp.ui.common.MainTopBar
import com.example.marvelapp.ui.screens.comics.viewmodel.ComicsViewModel

@Composable
fun ComicsScreenState(
    characterId: Int,
    onBackButton: () -> Unit,
    comicsViewModel: ComicsViewModel = hiltViewModel()
) {

    val state by comicsViewModel.state.collectAsState()
    comicsViewModel.fetchComics(characterId = characterId)

    when (state) {
        is ComicsViewModel.UIState.Loading -> {
            LoadingScreen()
        }
        is ComicsViewModel.UIState.Success<*> -> {
            ComicsScreen(
                onBackButton = onBackButton,
                data = (state as ComicsViewModel.UIState.Success<*>).data
            )
        }
        is ComicsViewModel.UIState.Error -> {
            ErrorScreen(message = (state as ComicsViewModel.UIState.Error).message)
        }
    }
}

@Composable
fun <T> ComicsScreen(
    onBackButton: () -> Unit,
    data: List<T>
) {
    val state = rememberLazyListState()
    MarvelAppScreen {
        Scaffold(
            topBar = {
                MainTopBar(
                    showBackButton = true,
                    barTitle = stringResource(id = R.string.comics_TEXT),
                    onBackClick = onBackButton
                )
            }
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = dimensionResource(id = R.dimen.dimen_16_dp))
                ) {
                    Text(
                        text = stringResource(id = R.string.comics_TEXT),
                        style = MaterialTheme.typography.body1
                    )
                    LazyColumn(state = state) {
                        items(data) { item ->
                            ComicRow(title = (item as ApiReference).name)
                        }
                    }
                }
            }
        }
    }
}