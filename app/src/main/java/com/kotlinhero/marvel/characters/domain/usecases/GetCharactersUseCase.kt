package com.kotlinhero.marvel.characters.domain.usecases

import com.kotlinhero.marvel.characters.domain.repositories.CharactersRemoteRepository

class GetCharactersUseCase(
    private val charactersRemoteRepository: CharactersRemoteRepository
) {
    suspend operator fun invoke() = charactersRemoteRepository.getCharacters()
}