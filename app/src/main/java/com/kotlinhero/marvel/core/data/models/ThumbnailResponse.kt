package com.kotlinhero.marvel.core.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ThumbnailResponse(
    @SerialName("path")
    val path: String,
    @SerialName("extension")
    val extension: String
) {
    val fullPath get() = "$path.$extension"
}