package com.kotlinhero.marvel.characters.data.remote.api

import com.kotlinhero.marvel.characters.data.remote.models.CharacterDto
import com.kotlinhero.marvel.network.data.env.DEV_ROUTE
import com.kotlinhero.marvel.network.data.env.PUBLIC_API_KEY
import com.kotlinhero.marvel.network.data.env.generateApiHash
import com.kotlinhero.marvel.network.data.env.generateTimeStamp
import com.kotlinhero.marvel.network.data.models.DataResponse
import com.kotlinhero.marvel.network.data.models.PaginationResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class CharactersApi(private val httpClient: HttpClient) {
    suspend fun getCharacters(): List<CharacterDto> {
        val timeStamp = generateTimeStamp()
        return httpClient.get("$DEV_ROUTE/characters") {
            parameter("ts", timeStamp)
            parameter("apikey", PUBLIC_API_KEY)
            parameter("hash", generateApiHash(timeStamp))
        }
            .body<DataResponse<PaginationResponse<CharacterDto>>>()
            .data
            .results
    }
}