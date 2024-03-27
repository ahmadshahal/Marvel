package com.kotlinhero.marvel.common.ui.providers

import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController


val LocalNavController =
    compositionLocalOf<NavHostController> { error("No NavHostController Provided") }