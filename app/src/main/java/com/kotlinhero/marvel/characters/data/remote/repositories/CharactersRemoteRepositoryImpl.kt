package com.kotlinhero.marvel.characters.data.remote.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.kotlinhero.marvel.characters.data.mappers.toCharacter
import com.kotlinhero.marvel.characters.data.remote.api.CharactersApi
import com.kotlinhero.marvel.characters.data.remote.paging.CharactersPagingSource
import com.kotlinhero.marvel.characters.domain.entities.Character
import com.kotlinhero.marvel.characters.domain.repositories.CharactersRemoteRepository
import com.kotlinhero.marvel.common.NETWORK_PAGE_SIZE
import com.kotlinhero.marvel.common.repositories.BaseRemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CharactersRemoteRepositoryImpl(
    private val charactersApi: CharactersApi
) : BaseRemoteRepository(), CharactersRemoteRepository {
    override suspend fun getCharacters() = runCatching {
        charactersApi.getCharacters().map { it.toCharacter() }
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