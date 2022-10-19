package com.example.marvelapp.ui.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.marvelapp.ui.screens.characters.views.CharactersScreenState
import com.example.marvelapp.ui.screens.comics.views.ComicsScreenState

@ExperimentalFoundationApi
@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.CharactersScreen.route
    ) {
        addCharactersScreen(navController, this)
        addComicsScreen(navController, this)
    }
}

@ExperimentalFoundationApi
private fun addCharactersScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(route = Screens.CharactersScreen.route) {
        CharactersScreenState(
            onBackButton = { navController.popBackStack() },
            navigateToComics = { characterId ->
                navController.navigate(Screens.ComicsScreen.withArgs(characterId.toString()))
            }
        )
    }
}

private fun addComicsScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(
        route = Screens.ComicsScreen.withArgsFormat(Screens.ComicsScreen.characterId),
        arguments = listOf(
            navArgument(Screens.ComicsScreen.characterId) {
                type = NavType.IntType
            }
        )
    ) { navBackStackEntry ->
        val args = navBackStackEntry.arguments

        ComicsScreenState(
            characterId = args?.getInt(Screens.ComicsScreen.characterId) ?: 0,
            onBackButton = { navController.popBackStack() })
    }
}