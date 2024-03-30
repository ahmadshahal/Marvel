package com.kotlinhero.marvel.features.characters.data.remote.api

import com.kotlinhero.marvel.core.data.models.DataResponse
import com.kotlinhero.marvel.core.data.models.PaginationResponse
import com.kotlinhero.marvel.features.characters.data.remote.models.CharacterDto
import com.kotlinhero.marvel.features.characters.data.remote.models.ComicDto
import com.kotlinhero.marvel.features.characters.data.remote.models.EventDto
import com.kotlinhero.marvel.features.characters.data.remote.models.SerieDto
import com.kotlinhero.marvel.features.characters.data.remote.models.StoryDto
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

    suspend fun getEvents(characterId: Int): List<EventDto> {
        return httpClient.get("characters/$characterId/events") {
            parameter("limit", 3)
            parameter("offset", 0)
        }
            .body<DataResponse<PaginationResponse<EventDto>>>()
            .data
            .results
    }

    suspend fun getSeries(characterId: Int): List<SerieDto> {
        return httpClient.get("characters/$characterId/series") {
            parameter("limit", 3)
            parameter("offset", 0)
        }
            .body<DataResponse<PaginationResponse<SerieDto>>>()
            .data
            .results
    }

    suspend fun getStories(characterId: Int): List<StoryDto> {
        return httpClient.get("characters/$characterId/stories") {
            parameter("limit", 3)
            parameter("offset", 0)
        }
            .body<DataResponse<PaginationResponse<StoryDto>>>()
            .data
            .results
    }
}