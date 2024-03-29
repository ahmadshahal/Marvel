package com.kotlinhero.marvel.characters.domain.usecases

import com.kotlinhero.marvel.characters.domain.repositories.CharactersRepository

class GetStoriesUseCase(private val charactersRepository: CharactersRepository) {
    suspend operator fun invoke(characterId: Int) = charactersRepository.getStories(characterId)
}