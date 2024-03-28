package com.kotlinhero.marvel.characters.ui.components

import androidx.compose.animation.AnimatedContent
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.kotlinhero.marvel.R
import com.kotlinhero.marvel.characters.domain.entities.Character
import com.kotlinhero.marvel.common.ui.reusables.error.HorizontalErrorBox
import com.kotlinhero.marvel.common.ui.reusables.error.VerticalErrorBox
import com.kotlinhero.marvel.common.ui.reusables.image.NetworkImage

@Composable
fun CharactersLazyGrid(
    modifier: Modifier = Modifier,
    characterPagingItems: LazyPagingItems<Character>,
    onClickCharacter: (Int) -> Unit,
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(top = 8.dp, start = 24.dp, end = 24.dp, bottom = 8.dp),
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
        items(characterPagingItems.itemCount) { index ->
            val character = characterPagingItems[index]!!
            CharacterItem(
                name = character.name,
                thumbnail = character.thumbnail,
                onClick = { onClickCharacter(character.id) }
            )
        }
        item(span = { GridItemSpan(2) }) {
            AnimatedContent(targetState = characterPagingItems.loadState, label = "") {
                when {
                    it.refresh is LoadState.Loading -> RefreshLoadingBox()
                    it.refresh is LoadState.Error -> {
                        val error = characterPagingItems.loadState.refresh as LoadState.Error
                        RefreshErrorBox(
                            message = error.error.localizedMessage ?: "",
                            onClickTryAgain = characterPagingItems::retry
                        )
                    }
                    it.append is LoadState.Loading -> AppendLoadingBox()
                    it.append is LoadState.Error -> {
                        val error = characterPagingItems.loadState.append as LoadState.Error
                        AppendErrorBox(
                            message = error.error.localizedMessage ?: "",
                            onClickTryAgain = characterPagingItems::retry
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun RefreshLoadingBox(modifier: Modifier = Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        CircularProgressIndicator(
            modifier = Modifier.size(24.dp),
            strokeCap = StrokeCap.Round,
        )
    }
}

@Composable
private fun AppendLoadingBox(modifier: Modifier = Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        CircularProgressIndicator(
            modifier = Modifier.size(20.dp),
            strokeCap = StrokeCap.Round,
            strokeWidth = 3.dp
        )
    }
}

@Composable
private fun RefreshErrorBox(
    modifier: Modifier = Modifier,
    message: String,
    onClickTryAgain: () -> Unit,
) {
    VerticalErrorBox(
        modifier = modifier,
        message = message,
        onClickTryAgain = onClickTryAgain
    )
}

@Composable
private fun AppendErrorBox(
    modifier: Modifier = Modifier,
    message: String,
    onClickTryAgain: () -> Unit,
) {
    HorizontalErrorBox(
        modifier = modifier,
        message = message,
        onClickTryAgain = onClickTryAgain
    )
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
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.8F)
                        ),
                    )
                )
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