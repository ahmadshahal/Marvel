package com.kotlinhero.marvel.common.ui.navigation

sealed class Destination(val route: String) {
    data object CharactersScreen : Destination(route = "/characters_screen")
}
