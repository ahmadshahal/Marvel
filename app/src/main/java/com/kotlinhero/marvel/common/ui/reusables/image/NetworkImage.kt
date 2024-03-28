package com.kotlinhero.marvel.common.ui.reusables.image

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.kotlinhero.marvel.R
import com.kotlinhero.marvel.common.ui.reusables.shimmer.shimmerBrush

@Composable
fun NetworkImage(
    modifier: Modifier = Modifier,
    showShimmer: Boolean = true,
    colorFilter: ColorFilter? = null,
    contentScale: ContentScale = ContentScale.Crop,
    url: String,
) {
    var loading by rememberSaveable { mutableStateOf(false) }
    Box(modifier = modifier) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = url,
            contentDescription = null,
            contentScale = contentScale,
            onLoading = { loading = true },
            onSuccess = { loading = false },
            onError = { loading = false },
            colorFilter = colorFilter,
            error = painterResource(id = R.drawable.ic_marvel_logo),
        )
        val shimmerBrush = shimmerBrush()
        AnimatedVisibility(
            visible = loading && showShimmer,
            label = "",
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxSize()
                    .background(brush = shimmerBrush)
            )
        }
    }
}