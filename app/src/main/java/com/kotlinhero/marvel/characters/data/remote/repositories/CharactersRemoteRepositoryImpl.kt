package com.kotlinhero.marvel.characters.data.remote.repositories

import com.kotlinhero.marvel.characters.data.mappers.toCharacter
import com.kotlinhero.marvel.characters.data.remote.api.CharactersApi
import com.kotlinhero.marvel.characters.domain.repositories.CharactersRemoteRepository
import com.kotlinhero.marvel.common.repositories.BaseRemoteRepository

class CharactersRemoteRepositoryImpl(
    private val charactersApi: CharactersApi
) : BaseRemoteRepository(), CharactersRemoteRepository {
    override suspend fun getCharacters() = runCatching {
        charactersApi.getCharacters().map { it.toCharacter() }
    }
}