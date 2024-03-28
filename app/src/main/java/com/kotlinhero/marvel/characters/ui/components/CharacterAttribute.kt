package com.kotlinhero.marvel.characters.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kotlinhero.marvel.R
import com.kotlinhero.marvel.characters.domain.entities.Character
import com.kotlinhero.marvel.common.ui.theme.White

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
            value = "${character.age} Years"
        )
        CharacterAttribute(
            painter = painterResource(id = R.drawable.ic_weight),
            value = "${character.weight} Kg"
        )
        CharacterAttribute(
            painter = painterResource(id = R.drawable.ic_height),
            value = "${character.height} m"
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
        Icon(painter = painter, contentDescription = null, tint = White)
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = value, fontWeight = FontWeight.Medium, color = White, fontSize = 12.sp)
    }
}