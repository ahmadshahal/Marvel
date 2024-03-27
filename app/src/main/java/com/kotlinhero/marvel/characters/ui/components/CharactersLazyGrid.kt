package com.kotlinhero.marvel.characters.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kotlinhero.marvel.R
import com.kotlinhero.marvel.characters.domain.models.Character
import com.kotlinhero.marvel.common.ui.reusables.image.NetworkImage

@Composable
fun CharactersLazyGrid(
    modifier: Modifier = Modifier,
    characters: List<Character>,
    onClickCharacter: (Int) -> Unit,
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(top = 8.dp, start = 24.dp, end = 24.dp, bottom = 24.dp),
    ) {
        item(span = { GridItemSpan(2) }) {
            Column {
                Text(
                    text = stringResource(R.string.welcome_to_the_world_of_excitement),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.secondary,
                    lineHeight = 20.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = stringResource(R.string.browse_your_favourite_characters),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Black,
                    color = MaterialTheme.colorScheme.onBackground,
                    lineHeight = 34.sp
                )
            }
        }
        item(span = { GridItemSpan(2) }) {
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Image(
                    modifier = Modifier.size(64.dp),
                    painter = painterResource(id = R.drawable.hero_category),
                    contentDescription = null
                )
                Image(
                    modifier = Modifier.size(64.dp),
                    painter = painterResource(id = R.drawable.villain_category),
                    contentDescription = null
                )
                Image(
                    modifier = Modifier.size(64.dp),
                    painter = painterResource(id = R.drawable.alien_category),
                    contentDescription = null
                )
                Image(
                    modifier = Modifier.size(64.dp),
                    painter = painterResource(id = R.drawable.antihero_category),
                    contentDescription = null
                )
                Image(
                    modifier = Modifier.size(64.dp),
                    painter = painterResource(id = R.drawable.human_category),
                    contentDescription = null
                )
            }
        }
        items(characters) {
            CharacterItem(
                name = it.name,
                thumbnail = it.thumbnail,
                onClick = { onClickCharacter(it.id) }
            )
        }
    }
}


@Composable
private fun CharacterItem(
    modifier: Modifier = Modifier,
    name: String,
    thumbnail: String,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .height(230.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = onClick),
    ) {
        NetworkImage(url = thumbnail, modifier = Modifier.matchParentSize())
        Spacer(
            modifier = Modifier
                .matchParentSize()
                .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4F))
        )
        Text(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(12.dp),
            text = name,
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.onPrimary,
            fontWeight = FontWeight.ExtraBold
        )
    }
}