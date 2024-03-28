package com.kotlinhero.marvel.characters.data.remote.api

import com.kotlinhero.marvel.characters.data.remote.models.CharacterDto
import com.kotlinhero.marvel.network.data.models.DataResponse
import com.kotlinhero.marvel.network.data.models.PaginationResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class CharactersApi(private val httpClient: HttpClient) {
    suspend fun getCharacters(): List<CharacterDto> {
        return httpClient.get("characters")
            .body<DataResponse<PaginationResponse<CharacterDto>>>()
            .data
            .results
    }
}