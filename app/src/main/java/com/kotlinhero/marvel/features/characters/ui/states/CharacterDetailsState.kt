package com.kotlinhero.marvel.features.characters.ui.states

import com.kotlinhero.marvel.core.ui.states.FetchState
import com.kotlinhero.marvel.features.characters.domain.entities.Character
import com.kotlinhero.marvel.features.characters.domain.entities.publications.Comic
import com.kotlinhero.marvel.features.characters.domain.entities.publications.Event
import com.kotlinhero.marvel.features.characters.domain.entities.publications.Serie
import com.kotlinhero.marvel.features.characters.domain.entities.publications.Story
import com.kotlinhero.marvel.features.characters.domain.enums.PublicationType

data class CharacterDetailsState(
    val fetchState: FetchState<CharacterDetails> = FetchState.Initial(),
    val selectedPublicationType: PublicationType = PublicationType.COMICS,
)

data class CharacterDetails(
    val character: Character,
    val comics: List<Comic>,
    val events: List<Event>,
    val series: List<Serie>,
    val stories: List<Story>
)