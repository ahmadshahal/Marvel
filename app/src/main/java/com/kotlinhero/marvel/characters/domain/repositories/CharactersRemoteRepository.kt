package com.kotlinhero.marvel.characters.domain.repositories

import com.kotlinhero.marvel.characters.domain.entities.Character

interface CharactersRemoteRepository {
    suspend fun getCharacters(): Result<List<Character>>
}