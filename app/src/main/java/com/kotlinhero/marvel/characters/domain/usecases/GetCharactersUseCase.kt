package com.kotlinhero.marvel.characters.domain.usecases

import com.kotlinhero.marvel.characters.domain.repositories.CharactersRepository

class GetCharactersUseCase(private val charactersRepository: CharactersRepository) {
    suspend operator fun invoke() = charactersRepository.getCharactersPagingFlow()
}