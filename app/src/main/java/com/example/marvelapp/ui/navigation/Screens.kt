package com.example.marvelapp.ui.navigation

sealed class Screens(val route: String) {
    object CharactersScreen : Screens("characters_screen")
    object ComicsScreen : Screens("comics_screen") {
        const val characterId = "characterId"
    }

    // build navigation path (for screen navigation)
    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }

    // build and setup route format (in navigation graph)
    fun withArgsFormat(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/{$arg}")
            }
        }
    }

}