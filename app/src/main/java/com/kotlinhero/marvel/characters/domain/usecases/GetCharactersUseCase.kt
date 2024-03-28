package com.kotlinhero.marvel.characters.domain.usecases

import com.kotlinhero.marvel.characters.data.remote.repositories.CharactersRemoteRepository

class GetCharactersUseCase(
    private val charactersRemoteRepository: CharactersRemoteRepository
) {
    suspend operator fun invoke() = runCatching {
        throw Exception()
        charactersRemoteRepository.getCharacters()
    }
}