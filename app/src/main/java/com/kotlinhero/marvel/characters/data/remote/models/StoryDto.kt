package com.kotlinhero.marvel.characters.data.remote.models

import com.kotlinhero.marvel.network.data.models.ThumbnailResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StoryDto(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String?,
    @SerialName("description")
    val description: String?,
    @SerialName("thumbnail")
    val thumbnail: ThumbnailResponse?,
)
