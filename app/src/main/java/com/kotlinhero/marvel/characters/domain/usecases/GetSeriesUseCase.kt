package com.kotlinhero.marvel.characters.domain.usecases

import com.kotlinhero.marvel.characters.domain.repositories.CharactersRepository

class GetSeriesUseCase(private val charactersRepository: CharactersRepository) {
    suspend operator fun invoke(characterId: Int) = charactersRepository.getSeries(characterId)
}