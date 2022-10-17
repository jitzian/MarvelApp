package com.example.marvelapp.ui.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.marvelapp.ui.screens.characters.views.CharactersScreenState
import com.example.marvelapp.ui.screens.comics.views.ComicsScreen

@ExperimentalFoundationApi
@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.CharactersScreen.route
    ) {
        composable(route = Screens.CharactersScreen.route) {
            CharactersScreenState(navController)
        }
        composable(route = Screens.ComicsScreen.route) {
            ComicsScreen(navController)
        }
    }
}