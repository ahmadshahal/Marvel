package com.kotlinhero.marvel.characters.ui.screens

import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kotlinhero.marvel.R
import com.kotlinhero.marvel.characters.domain.entities.Character
import com.kotlinhero.marvel.characters.ui.components.CharacterAttributes
import com.kotlinhero.marvel.characters.ui.components.ComicsLazyRow
import com.kotlinhero.marvel.characters.ui.viewmodels.CharacterDetailsViewModel
import com.kotlinhero.marvel.common.ui.providers.LocalNavController
import com.kotlinhero.marvel.common.ui.reusables.error.NetflixErrorBox
import com.kotlinhero.marvel.common.ui.reusables.image.NetworkImage
import com.kotlinhero.marvel.common.ui.reusables.loading.NetflixLoadingBox
import com.kotlinhero.marvel.common.ui.states.FetchState
import org.koin.androidx.compose.koinViewModel

@Composable
fun CharacterDetailsScreen(viewModel: CharacterDetailsViewModel = koinViewModel()) {
    val activity = LocalContext.current as ComponentActivity
    LaunchedEffect(key1 = Unit) {
        activity.enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(android.graphics.Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.dark(android.graphics.Color.TRANSPARENT)
        )
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Black,
        topBar = { CharacterDetailsTopBar() }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    bottom = paddingValues.calculateBottomPadding(),
                    start = paddingValues.calculateStartPadding(LayoutDirection.Ltr),
                    end = paddingValues.calculateEndPadding(LayoutDirection.Ltr)
                ),
            contentAlignment = Alignment.Center
        ) {
            AnimatedContent(
                targetState = viewModel.characterDetailsState.fetchState,
                contentAlignment = Alignment.Center,
                label = "",
                transitionSpec = { fadeIn() togetherWith fadeOut() }
            ) {
                when (val fetchState = it) {
                    is FetchState.Error -> NetflixErrorBox(
                        modifier = Modifier.padding(horizontal = 24.dp),
                        message = fetchState.message.asString(),
                        onClickTryAgain = viewModel::getData
                    )
                    is FetchState.Success -> {
                        val character = viewModel.character
                        CharacterBackground(thumbnail = character.thumbnail)
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Bottom
                        ) {
                            CharacterDetails(
                                modifier = Modifier.padding(horizontal = 24.dp),
                                character = character
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            val comics = viewModel.comics
                            ComicsLazyRow(comics = comics)
                        }
                    }
                    else -> NetflixLoadingBox()
                }
            }
        }
    }
}

@Composable
private fun BoxScope.CharacterBackground(thumbnail: String) {
    NetworkImage(
        url = thumbnail,
        modifier = Modifier.fillMaxSize(),
        showShimmer = false
    )
    Spacer(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color.Transparent,
                        Color.Black.copy(alpha = 0.8F),
                        Color.Black
                    ),
                )
            )
    )
}

@Composable
private fun ColumnScope.CharacterDetails(modifier: Modifier = Modifier, character: Character) {
    Column(modifier = modifier) {
        Text(
            text = character.name,
            fontSize = 40.sp,
            color = MaterialTheme.colorScheme.onPrimary,
            fontWeight = FontWeight.ExtraBold,
            lineHeight = 56.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        CharacterAttributes(modifier = Modifier.fillMaxWidth(), character = character)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = character.description,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onPrimary,
            fontWeight = FontWeight.Medium
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CharacterDetailsTopBar() {
    val navController = LocalNavController.current
    TopAppBar(
        modifier = Modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
        ),
        navigationIcon = {
            IconButton(onClick = navController::navigateUp) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.background
                )
            }
        },
        title = { }
    )
}