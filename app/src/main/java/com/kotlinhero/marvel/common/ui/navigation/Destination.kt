package com.kotlinhero.marvel.common.ui.navigation

sealed class Destination(val route: String) {
    data object CharactersScreen : Destination(route = "/characters_screen")
    data object CharacterDetailsScreen : Destination(route = "/character_details_screen")
}
