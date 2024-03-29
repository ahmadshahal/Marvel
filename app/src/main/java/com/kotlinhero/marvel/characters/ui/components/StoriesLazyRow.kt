package com.kotlinhero.marvel.characters.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kotlinhero.marvel.R
import com.kotlinhero.marvel.characters.domain.entities.Story
import com.kotlinhero.marvel.characters.domain.enums.ProductType
import com.kotlinhero.marvel.common.ui.reusables.image.NetworkImage

@Composable
fun StoriesLazyRow(
    modifier: Modifier = Modifier,
    stories: List<Story>,
    onProductTypeChange: (ProductType) -> Unit
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.stories),
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.background,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier.clickable { onProductTypeChange(ProductType.COMICS) },
                text = stringResource(id = R.string.view_comics),
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.background,
                fontWeight = FontWeight.Medium,
                textDecoration = TextDecoration.Underline
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        when(stories.isNotEmpty()) {
            true -> LazyRow(
                modifier = modifier,
                verticalAlignment =Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(horizontal = 24.dp),
            ) {
                items(stories) {
                    StoryItem(story = it)
                }
            }
            false -> Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .padding(horizontal = 32.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.hasn_t_participated_in_any_stories),
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.background
                )
            }
        }
    }
}

@Composable
private fun StoryItem(modifier: Modifier = Modifier, story: Story) {
    Box(
        modifier = modifier
            .size(140.dp)
            .clip(RoundedCornerShape(16.dp)),
    ) {
        NetworkImage(url = story.thumbnail, modifier = Modifier.matchParentSize())
        Spacer(
            modifier = Modifier
                .matchParentSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.75F),
                            Color.Black
                        ),
                    )
                )
        )
        Text(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(12.dp),
            text = story.title,
            fontSize = 13.sp,
            color = MaterialTheme.colorScheme.onPrimary,
            fontWeight = FontWeight.ExtraBold,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}