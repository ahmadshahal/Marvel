package com.kotlinhero.marvel.features.characters.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kotlinhero.marvel.R
import com.kotlinhero.marvel.features.characters.domain.entities.Character

@Composable
fun CharacterAttributes(
    modifier: Modifier = Modifier,
    character: Character,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CharacterAttribute(
            painter = painterResource(id = R.drawable.ic_age),
            value = character.age + " " + stringResource(R.string.years)
        )
        CharacterAttribute(
            painter = painterResource(id = R.drawable.ic_weight),
            value = character.weight + " " + stringResource(R.string.kg)
        )
        CharacterAttribute(
            painter = painterResource(id = R.drawable.ic_height),
            value = character.height + stringResource(R.string.m)
        )
        CharacterAttribute(
            painter = painterResource(id = R.drawable.ic_location),
            value = character.location
        )
    }
}

@Composable
private fun CharacterAttribute(
    modifier: Modifier = Modifier,
    painter: Painter,
    value: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painter,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onPrimaryContainer
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = value,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontSize = 12.sp
        )
    }
}