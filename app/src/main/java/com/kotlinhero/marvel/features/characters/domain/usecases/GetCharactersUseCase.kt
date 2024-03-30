package com.kotlinhero.marvel.features.characters.domain.usecases

import com.kotlinhero.marvel.features.characters.domain.repositories.CharactersRepository

class GetCharactersUseCase(private val charactersRepository: CharactersRepository) {
    suspend operator fun invoke() = charactersRepository.getCharactersPagingFlow()
}