package com.kotlinhero.marvel.common.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataResponse<T>(
    @SerialName("status")
    val status: String,
    @SerialName("data")
    val data: T,
)
