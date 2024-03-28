package com.kotlinhero.marvel.characters.data.remote.api

import com.kotlinhero.marvel.characters.data.remote.models.CharacterDto
import com.kotlinhero.marvel.characters.data.remote.models.ComicDto
import com.kotlinhero.marvel.network.data.models.DataResponse
import com.kotlinhero.marvel.network.data.models.PaginationResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class CharactersApi(private val httpClient: HttpClient) {
    suspend fun getCharacters(skip: Int = 0, limit: Int = 20): List<CharacterDto> {
        return httpClient.get("characters") {
            parameter("limit", limit)
            parameter("offset", skip)
        }
            .body<DataResponse<PaginationResponse<CharacterDto>>>()
            .data
            .results
    }

    suspend fun getCharacter(id: Int): CharacterDto {
        return httpClient.get("characters/$id")
            .body<DataResponse<PaginationResponse<CharacterDto>>>()
            .data
            .results
            .first()
    }

    suspend fun getComics(characterId: Int): List<ComicDto> {
        return httpClient.get("characters/$characterId/comics") {
            parameter("limit", 3)
            parameter("offset", 0)
        }
            .body<DataResponse<PaginationResponse<ComicDto>>>()
            .data
            .results
    }
}