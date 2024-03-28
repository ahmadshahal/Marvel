package com.kotlinhero.marvel.characters.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kotlinhero.marvel.R
import com.kotlinhero.marvel.characters.domain.entities.Character
import com.kotlinhero.marvel.characters.ui.viewmodels.CharacterDetailsViewModel
import com.kotlinhero.marvel.common.ui.providers.LocalNavController
import com.kotlinhero.marvel.common.ui.reusables.error.VerticalErrorBox
import com.kotlinhero.marvel.common.ui.reusables.image.NetworkImage
import com.kotlinhero.marvel.common.ui.states.FetchState
import com.kotlinhero.marvel.common.ui.theme.White
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailsScreen(viewModel: CharacterDetailsViewModel = koinViewModel()) {
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
                targetState = viewModel.characterFetchState,
                contentAlignment = Alignment.Center,
                label = "",
                transitionSpec = { fadeIn() togetherWith fadeOut() }
            ) {
                when (val fetchState = it) {
                    is FetchState.Loading -> CircularProgressIndicator(
                        modifier = Modifier.size(28.dp),
                        strokeCap = StrokeCap.Round
                    )
                    is FetchState.Error -> VerticalErrorBox(onClickTryAgain = viewModel::getCharacter)
                    is FetchState.Success -> {
                        val character = fetchState.data ?: Character()
                        NetworkImage(url = character.thumbnail, modifier = Modifier.fillMaxSize())
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
                        CharacterDetails(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 24.dp),
                            character = character
                        )
                    }

                    else -> Unit
                }
            }
        }
    }
}

@Composable
private fun CharacterDetails(modifier: Modifier = Modifier, character: Character) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(bottom = 32.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(
            text = character.name,
            fontSize = 40.sp,
            color = MaterialTheme.colorScheme.onPrimary,
            fontWeight = FontWeight.ExtraBold,
            lineHeight = 56.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            CharacterAttribute(icon = painterResource(id = R.drawable.ic_age), value = "${character.age} Years")
            CharacterAttribute(icon = painterResource(id = R.drawable.ic_weight), value = "${character.weight} Kg")
            CharacterAttribute(icon = painterResource(id = R.drawable.ic_height), value = "${character.height} m")
            CharacterAttribute(icon = painterResource(id = R.drawable.ic_location), value = character.location)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = character.description,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onPrimary,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
private fun CharacterAttribute(
    modifier: Modifier = Modifier,
    icon: Painter,
    value: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(painter = icon, contentDescription = null, tint = White)
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = value, fontWeight = FontWeight.Medium, color = White, fontSize = 12.sp)
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