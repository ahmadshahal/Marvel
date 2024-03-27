package com.kotlinhero.marvel.characters.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.kotlinhero.marvel.R

@Composable
fun CharactersScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopBar()
        }
    ) {
        Column(modifier = Modifier.padding(it)) {

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar() {
    CenterAlignedTopAppBar(
        title = {
            Image(
                painter = painterResource(id = R.drawable.ic_marvel_logo),
                contentDescription = null,
            )
        },
        modifier = Modifier,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_hamburger),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    )
}