package com.kotlinhero.marvel.core.ui.navigation

sealed class Destination(val route: String) {
    data object CharactersScreen : Destination(route = "/characters_screen")
    data object CharacterDetailsScreen : Destination(route = "/character_details_screen")
}
