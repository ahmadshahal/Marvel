package com.kotlinhero.marvel.network.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    @SerialName("message")
    val message: String,
    @SerialName("code")
    val code: String
)