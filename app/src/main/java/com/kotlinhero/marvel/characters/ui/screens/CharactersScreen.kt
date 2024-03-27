package com.kotlinhero.marvel.characters.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import com.kotlinhero.marvel.R
import com.kotlinhero.marvel.characters.ui.components.CharactersLazyGrid
import com.kotlinhero.marvel.characters.ui.viewmodels.viewmodel.CharactersViewModel
import com.kotlinhero.marvel.common.ui.states.FetchState
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersScreen(viewModel: CharactersViewModel = koinViewModel()) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    Scaffold(
        modifier = Modifier.fillMaxSize().nestedScroll(scrollBehavior.nestedScrollConnection),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = { CharactersTopBar(scrollBehavior) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues), contentAlignment = Alignment.Center
        ) {
            AnimatedContent(
                contentAlignment = Alignment.Center,
                targetState = viewModel.fetchState,
                label = ""
            ) {
                when (it) {
                    is FetchState.Loading -> CircularProgressIndicator(strokeCap = StrokeCap.Round)
                    is FetchState.Error -> Text(text = "Error")
                    is FetchState.Success -> CharactersLazyGrid(
                        characters = viewModel.characters,
                        onClickCharacter = {}
                    )
                    else -> Unit
                }
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