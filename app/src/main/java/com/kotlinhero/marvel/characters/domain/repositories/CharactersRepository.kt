package com.kotlinhero.marvel.characters.domain.repositories

import androidx.paging.PagingData
import com.kotlinhero.marvel.characters.domain.entities.Character
import com.kotlinhero.marvel.characters.domain.entities.publications.Comic
import com.kotlinhero.marvel.characters.domain.entities.publications.Event
import com.kotlinhero.marvel.characters.domain.entities.publications.Serie
import com.kotlinhero.marvel.characters.domain.entities.publications.Story
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    suspend fun getCharacter(id: Int): Result<Character>

    suspend fun getCharacters(): Result<List<Character>>

    suspend fun getComics(characterId: Int): Result<List<Comic>>

    suspend fun getEvents(characterId: Int): Result<List<Event>>

    suspend fun getSeries(characterId: Int): Result<List<Serie>>

    suspend fun getStories(characterId: Int): Result<List<Story>>

    suspend fun getCharactersPagingFlow(): Flow<PagingData<Character>>
}