package com.kotlinhero.marvel.features.characters.domain.usecases

import com.kotlinhero.marvel.features.characters.domain.repositories.CharactersRepository

class GetStoriesUseCase(private val charactersRepository: CharactersRepository) {
    suspend operator fun invoke(characterId: Int) = charactersRepository.getStories(characterId)
}