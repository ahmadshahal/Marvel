package com.kotlinhero.marvel.characters.domain.usecases

import com.kotlinhero.marvel.characters.domain.repositories.CharactersRepository

class GetCharacterUseCase(private val charactersRepository: CharactersRepository) {
    suspend operator fun invoke(id: Int) = charactersRepository.getCharacter(id)
}