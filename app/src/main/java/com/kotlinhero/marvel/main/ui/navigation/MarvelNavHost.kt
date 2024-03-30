package com.kotlinhero.marvel.main.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kotlinhero.marvel.core.ui.navigation.Destination
import com.kotlinhero.marvel.features.characters.ui.screens.CharacterDetailsScreen
import com.kotlinhero.marvel.features.characters.ui.screens.CharactersScreen

@Composable
fun MarvelNavHost(navController: NavHostController, startDestination: String) {
    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(route = Destination.CharactersScreen.route) {
            CharactersScreen()
        }
        composable(
            route = Destination.CharacterDetailsScreen.route + "/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {
            CharacterDetailsScreen()
        }
    }
}

