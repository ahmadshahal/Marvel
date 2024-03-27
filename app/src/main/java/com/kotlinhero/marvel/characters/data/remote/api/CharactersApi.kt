package com.kotlinhero.marvel.characters.data.remote.api

import com.kotlinhero.marvel.characters.data.remote.models.CharacterDto
import com.kotlinhero.marvel.common.data.models.DataResponse
import com.kotlinhero.marvel.common.data.models.PaginationResponse
import com.kotlinhero.marvel.common.data.routes.API_KEY
import com.kotlinhero.marvel.common.data.routes.DEV_ROUTE
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class CharactersApi(private val httpClient: HttpClient) {
    suspend fun getCharacters(): List<CharacterDto> =
        httpClient.get("$DEV_ROUTE/characters/$API_KEY")
            .body<DataResponse<PaginationResponse<CharacterDto>>>()
            .data
            .results
}