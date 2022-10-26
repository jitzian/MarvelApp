package com.example.marvelapp.ui.screens.characters.views

import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.marvelapp.R
import com.example.marvelapp.data.remote.model.ApiCharacter
import com.example.marvelapp.ui.app.MarvelAppScreen
import com.example.marvelapp.ui.common.ErrorScreen
import com.example.marvelapp.ui.common.ItemRow
import com.example.marvelapp.ui.common.LoadingScreen
import com.example.marvelapp.ui.common.MainTopBar
import com.example.marvelapp.ui.screens.characters.viewmodel.CharactersViewModel
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
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

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun <T> CharactersScreen(
    onBackButton: () -> Unit,
    navigateToComics: (Int) -> Unit,
    data: List<T>
) {
    val state = rememberLazyListState()
    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()

    BackPressedHandler(sheetState.isVisible) {
        coroutineScope.launch {
            sheetState.hide()
        }
    }

    MarvelAppScreen {
        Scaffold(
            topBar = {
                MainTopBar(
                    barTitle = stringResource(id = R.string.marvel_characters_TEXT),
                    onBackClick = onBackButton
                )
            }
        ) {
            Box(modifier = Modifier.fillMaxSize()) {

                var bottomSheetItem by remember {
                    mutableStateOf<T?>(null)
                }

                ModalBottomSheetLayout(
                    sheetContent = {
                        CharacterSummaryScreen(bottomSheetItem)
                    },
                    sheetState = sheetState
                ) {
                    LazyVerticalGrid(
                        state = state,
                        cells = GridCells.Adaptive(dimensionResource(id = R.dimen.dimen_180_dp)),
                        contentPadding = PaddingValues(dimensionResource(id = R.dimen.dimen_4_dp))
                    ) {
                        items(data) { item ->
                            ItemRow(
                                data = item,
                                onItemClick = {
                                    navigateToComics((item as ApiCharacter).id)
                                },
                                onMoreItemClick = {
                                    coroutineScope.launch {
                                        bottomSheetItem = it
                                        sheetState.show()
                                    }
                                }
                            )
                        }
                    }
                }

                /**
                 * Floating action button reference
                 * https://www.android--code.com/2021/04/jetpack-compose-lazycolumn-scroll-to.html
                 * */
                val isTopButtonVisible = remember {
                    derivedStateOf {
                        state.firstVisibleItemIndex > 0
                    }
                }

                FloatingActionButton(
                    onClick = {
                        coroutineScope.launch {
                            if (isTopButtonVisible.value) {
                                state.animateScrollToItem(0)
                            } else {
                                state.animateScrollToItem((data as List<*>).size)
                                state.animateScrollToItem((data as List<*>).size)
                            }
                        }
                    },
                    shape = RoundedCornerShape(50),
                    backgroundColor = Color(0xFFFF55A3),
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(dimensionResource(id = R.dimen.dimen_16_dp))
                ) {
                    if (isTopButtonVisible.value) {
                        Icon(Icons.Filled.ArrowUpward, "")
                    } else {
                        Icon(Icons.Filled.ArrowDownward, "")
                    }
                }

            }
        }
    }
}

/***
 * This function handles the back button action for dismissing the ModalBottomSheet
 * */
@Composable
fun BackPressedHandler(enabled: Boolean, onBack: () -> Unit) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val backDispatcher =
        requireNotNull(LocalOnBackPressedDispatcherOwner.current).onBackPressedDispatcher

    val backCallback = remember {
        object : OnBackPressedCallback(enabled) {
            override fun handleOnBackPressed() {
                onBack()
            }
        }
    }
    SideEffect {
        backCallback.isEnabled = enabled
    }

    DisposableEffect(key1 = backDispatcher, key2 = lifecycleOwner) {
        backDispatcher.addCallback(lifecycleOwner, backCallback)
        onDispose { backCallback.remove() }
    }
}