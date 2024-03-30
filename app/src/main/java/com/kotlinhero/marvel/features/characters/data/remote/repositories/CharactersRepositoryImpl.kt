package com.kotlinhero.marvel.features.characters.data.remote.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.kotlinhero.marvel.core.data.base.repositories.BaseRemoteRepository
import com.kotlinhero.marvel.core.utils.NETWORK_PAGE_SIZE
import com.kotlinhero.marvel.features.characters.data.remote.api.CharactersApi
import com.kotlinhero.marvel.features.characters.data.remote.mappers.toCharacter
import com.kotlinhero.marvel.features.characters.data.remote.mappers.toComic
import com.kotlinhero.marvel.features.characters.data.remote.mappers.toEvent
import com.kotlinhero.marvel.features.characters.data.remote.mappers.toSerie
import com.kotlinhero.marvel.features.characters.data.remote.mappers.toStory
import com.kotlinhero.marvel.features.characters.data.remote.paging.CharactersPagingSource
import com.kotlinhero.marvel.features.characters.domain.entities.Character
import com.kotlinhero.marvel.features.characters.domain.repositories.CharactersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CharactersRepositoryImpl(
    private val charactersApi: CharactersApi
) : BaseRemoteRepository(), CharactersRepository {
    override suspend fun getCharacter(id: Int) = runCatching {
        charactersApi.getCharacter(id).toCharacter()
    }

    override suspend fun getCharacters() = runCatching {
        charactersApi.getCharacters().map { it.toCharacter() }
    }

    override suspend fun getComics(characterId: Int) = runCatching {
        charactersApi.getComics(characterId).map { it.toComic() }
    }

    override suspend fun getEvents(characterId: Int) = runCatching {
        charactersApi.getEvents(characterId).map { it.toEvent() }
    }

    override suspend fun getStories(characterId: Int) = runCatching {
        charactersApi.getStories(characterId).map { it.toStory() }
    }

    override suspend fun getSeries(characterId: Int) = runCatching {
        charactersApi.getSeries(characterId).map { it.toSerie() }
    }

    override suspend fun getCharactersPagingFlow(): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, prefetchDistance = 2),
            pagingSourceFactory = {
                CharactersPagingSource(charactersApi)
            }
        ).flow.map { it.map { characterDto -> characterDto.toCharacter() } }
    }
}