package com.kotlinhero.marvel.characters.data.remote.models

import com.kotlinhero.marvel.network.data.models.ThumbnailResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterDto(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("thumbnail")
    val thumbnail: ThumbnailResponse
)
