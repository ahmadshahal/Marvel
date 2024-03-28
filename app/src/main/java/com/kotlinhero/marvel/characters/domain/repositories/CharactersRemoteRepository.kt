package com.kotlinhero.marvel.characters.domain.repositories

import androidx.paging.PagingData
import com.kotlinhero.marvel.characters.domain.entities.Character
import kotlinx.coroutines.flow.Flow

interface CharactersRemoteRepository {
    suspend fun getCharacters(): Result<List<Character>>

    suspend fun getCharactersPagingFlow(): Flow<PagingData<Character>>
}