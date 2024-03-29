package com.kotlinhero.marvel.characters.domain.repositories

import androidx.paging.PagingData
import com.kotlinhero.marvel.characters.domain.entities.Character
import com.kotlinhero.marvel.characters.domain.entities.Comic
import com.kotlinhero.marvel.characters.domain.entities.Event
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    suspend fun getCharacter(id: Int): Result<Character>

    suspend fun getCharacters(): Result<List<Character>>

    suspend fun getComics(characterId: Int): Result<List<Comic>>

    suspend fun getEvents(characterId: Int): Result<List<Event>>

    suspend fun getCharactersPagingFlow(): Flow<PagingData<Character>>
}