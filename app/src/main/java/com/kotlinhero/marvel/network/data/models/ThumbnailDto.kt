package com.kotlinhero.marvel.network.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ThumbnailDto(
    @SerialName("path")
    val path: String,
    @SerialName("extension")
    val extension: String
)