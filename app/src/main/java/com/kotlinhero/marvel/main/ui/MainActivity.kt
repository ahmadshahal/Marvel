package com.kotlinhero.marvel.main.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.kotlinhero.marvel.common.ui.navigation.Destination
import com.kotlinhero.marvel.common.ui.providers.LocalNavController
import com.kotlinhero.marvel.common.ui.theme.MarvelTheme
import com.kotlinhero.marvel.main.ui.navigation.MarvelNavHost
import com.kotlinhero.marvel.main.ui.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().setKeepOnScreenCondition {
            viewModel.splashScreenVisible
        }

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(
                android.graphics.Color.TRANSPARENT,
            ),
            navigationBarStyle = SystemBarStyle.dark(
                android.graphics.Color.TRANSPARENT,
            )
        )

        setContent {
            MarvelTheme {
                val navController = rememberNavController()
                CompositionLocalProvider(LocalNavController provides navController) {
                    MarvelNavHost(
                        navController = navController,
                        startDestination = Destination.CharactersScreen.route
                    )
                }
            }
        }
    }
}
