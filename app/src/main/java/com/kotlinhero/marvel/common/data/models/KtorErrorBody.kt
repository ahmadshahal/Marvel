package com.kotlinhero.marvel.common.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KtorErrorBody(
    @SerialName("message")
    val message: String,
    @SerialName("errors")
    val details: List<String>
)