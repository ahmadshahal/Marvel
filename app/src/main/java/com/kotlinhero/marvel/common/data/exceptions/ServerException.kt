package com.kotlinhero.marvel.common.data.exceptions

data class ServerException(
    override val message: String,
    val code: Int
) : Exception(message)