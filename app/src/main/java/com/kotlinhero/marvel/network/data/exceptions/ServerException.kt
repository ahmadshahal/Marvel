package com.kotlinhero.marvel.network.data.exceptions

data class ServerException(
    override val message: String,
    val code: Int
) : Exception(message)