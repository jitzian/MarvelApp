package com.example.marvelapp.ui.navigation

sealed class Screens(val route: String) {
    object CharactersScreen : Screens("characters_screen")
    object ComicsScreen : Screens("comics_screen")
}