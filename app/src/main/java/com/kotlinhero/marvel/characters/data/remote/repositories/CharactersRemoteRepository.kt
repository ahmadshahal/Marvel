package com.kotlinhero.marvel.characters.data.remote.repositories

import com.kotlinhero.marvel.characters.data.mappers.toCharacter
import com.kotlinhero.marvel.characters.data.remote.api.CharactersApi
import com.kotlinhero.marvel.characters.domain.models.Character
import com.kotlinhero.marvel.common.repositories.BaseRemoteRepository

class CharactersRemoteRepository(
    private val charactersApi: CharactersApi
) : BaseRemoteRepository() {
    suspend fun getCharacters(): List<Character> =
        charactersApi.getCharacters().map { it.toCharacter() }
}