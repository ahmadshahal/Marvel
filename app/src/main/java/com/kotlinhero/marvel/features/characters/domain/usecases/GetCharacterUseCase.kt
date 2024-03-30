package com.kotlinhero.marvel.features.characters.domain.usecases

import com.kotlinhero.marvel.features.characters.domain.repositories.CharactersRepository

class GetCharacterUseCase(private val charactersRepository: CharactersRepository) {
    suspend operator fun invoke(id: Int) = charactersRepository.getCharacter(id)
}