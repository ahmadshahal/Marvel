package com.kotlinhero.marvel.characters.ui.screens

import android.graphics.Color
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.kotlinhero.marvel.R
import com.kotlinhero.marvel.characters.domain.entities.Character
import com.kotlinhero.marvel.characters.ui.components.CharactersLazyGrid
import com.kotlinhero.marvel.characters.ui.viewmodels.CharactersViewModel
import com.kotlinhero.marvel.common.ui.navigation.Destination
import com.kotlinhero.marvel.common.ui.providers.LocalNavController
import com.kotlinhero.marvel.common.ui.reusables.buttons.BackToTopButton
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersScreen(viewModel: CharactersViewModel = koinViewModel()) {
    val activity = LocalContext.current as ComponentActivity
    LaunchedEffect(key1 = Unit) {
        activity.enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT)
        )
    }
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val coroutineScope = rememberCoroutineScope()
    val lazyGridState = rememberLazyGridState()
    val showScrollToTopButton by remember {
        derivedStateOf { lazyGridState.firstVisibleItemIndex > 2 }
    }
    val navController = LocalNavController.current
    val characterPagingItems: LazyPagingItems<Character> =
        viewModel.charactersState.collectAsLazyPagingItems()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = { CharactersTopBar(scrollBehavior) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = paddingValues.calculateTopPadding(),
                    start = paddingValues.calculateStartPadding(LayoutDirection.Ltr),
                    end = paddingValues.calculateEndPadding(LayoutDirection.Ltr)
                )
        ) {
            CharactersLazyGrid(
                characterPagingItems = characterPagingItems,
                onClickCharacter = {
                    navController.navigate(Destination.CharacterDetailsScreen.route + "/$it")
                },
                lazyGridState = lazyGridState
            )
            AnimatedVisibility(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 24.dp),
                visible = showScrollToTopButton,
                enter = slideInVertically { -it * 2 },
                exit = slideOutVertically { -it * 2 },
            ) {
                BackToTopButton(
                    onClick = {
                        coroutineScope.launch {
                            lazyGridState.animateScrollToItem(0)
                        }
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CharactersTopBar(scrollBehavior: TopAppBarScrollBehavior) {
    CenterAlignedTopAppBar(
        title = {
            Image(
                painter = painterResource(id = R.drawable.ic_marvel_logo),
                contentDescription = null,
            )
        },
        modifier = Modifier,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_hamburger),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}