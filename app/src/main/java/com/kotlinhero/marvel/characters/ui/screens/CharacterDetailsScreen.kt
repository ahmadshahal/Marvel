package com.kotlinhero.marvel.characters.ui.screens

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kotlinhero.marvel.R
import com.kotlinhero.marvel.characters.domain.entities.Character
import com.kotlinhero.marvel.characters.domain.entities.publications.Comic
import com.kotlinhero.marvel.characters.domain.entities.publications.Event
import com.kotlinhero.marvel.characters.domain.entities.publications.Serie
import com.kotlinhero.marvel.characters.domain.entities.publications.Story
import com.kotlinhero.marvel.characters.domain.enums.PublicationType
import com.kotlinhero.marvel.characters.ui.components.CharacterAttributes
import com.kotlinhero.marvel.characters.ui.components.PublicationLazyRow
import com.kotlinhero.marvel.characters.ui.viewmodels.CharacterDetailsViewModel
import com.kotlinhero.marvel.common.ui.providers.LocalNavController
import com.kotlinhero.marvel.common.ui.reusables.error.NetflixErrorBox
import com.kotlinhero.marvel.common.ui.reusables.image.NetworkImage
import com.kotlinhero.marvel.common.ui.reusables.loading.NetflixLoadingBox
import com.kotlinhero.marvel.common.ui.states.FetchState
import com.kotlinhero.marvel.common.utils.enableDarkEdgeToEdge
import org.koin.androidx.compose.koinViewModel

@Composable
fun CharacterDetailsScreen(viewModel: CharacterDetailsViewModel = koinViewModel()) {
    val context = LocalContext.current
    LaunchedEffect(key1 = Unit) {
        context.enableDarkEdgeToEdge()
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
                        CharacterBackground(thumbnail = viewModel.character.thumbnail)
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Bottom
                        ) {
                            CharacterDetails(
                                modifier = Modifier.padding(horizontal = 24.dp),
                                character = viewModel.character
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            PublicationsBox(
                                publicationType = viewModel.characterDetailsState.selectedPublicationType,
                                comics = viewModel.comics,
                                series = viewModel.series,
                                stories = viewModel.stories,
                                events = viewModel.events,
                                onPublicationTypeChange = viewModel::onPublicationTypeChange
                            )
                        }
                    }
                    else -> NetflixLoadingBox()
                }
            }
        }
    }
}

@Composable
private fun PublicationsBox(
    modifier: Modifier = Modifier,
    publicationType: PublicationType,
    comics: List<Comic>,
    series: List<Serie>,
    stories: List<Story>,
    events: List<Event>,
    onPublicationTypeChange: (PublicationType) -> Unit
) {
    AnimatedContent(
        modifier = modifier,
        targetState = publicationType,
        label = ""
    ) { productType ->
        when(productType) {
            PublicationType.COMICS -> PublicationLazyRow(
                publications = comics,
                onProductTypeChange = {
                    onPublicationTypeChange(PublicationType.SERIES)
                },
                title = stringResource(id = R.string.comics),
                emptyStatement = stringResource(id = R.string.hasn_t_participated_in_any_comics),
                nextPublicationTitle = stringResource(id = R.string.series)
            )
            PublicationType.SERIES -> PublicationLazyRow(
                publications = series,
                onProductTypeChange = {
                    onPublicationTypeChange(PublicationType.STORIES)
                },
                title = stringResource(id = R.string.series),
                emptyStatement = stringResource(id = R.string.hasn_t_participated_in_any_series),
                nextPublicationTitle = stringResource(id = R.string.stories)
            )
            PublicationType.STORIES -> PublicationLazyRow(
                publications = stories,
                onProductTypeChange = {
                    onPublicationTypeChange(PublicationType.EVENTS)
                },
                title = stringResource(id = R.string.stories),
                emptyStatement = stringResource(id = R.string.hasn_t_participated_in_any_stories),
                nextPublicationTitle = stringResource(id = R.string.events)
            )
            else -> PublicationLazyRow(
                publications = events,
                onProductTypeChange = {
                    onPublicationTypeChange(PublicationType.COMICS)
                },
                title = stringResource(id = R.string.events),
                emptyStatement = stringResource(id = R.string.hasn_t_participated_in_any_events),
                nextPublicationTitle = stringResource(id = R.string.comics)
            )
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