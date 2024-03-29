package com.kotlinhero.marvel.characters.ui.states

import com.kotlinhero.marvel.characters.domain.entities.Character
import com.kotlinhero.marvel.characters.domain.entities.publications.Comic
import com.kotlinhero.marvel.characters.domain.entities.publications.Event
import com.kotlinhero.marvel.characters.domain.entities.publications.Serie
import com.kotlinhero.marvel.characters.domain.entities.publications.Story
import com.kotlinhero.marvel.characters.domain.enums.PublicationType
import com.kotlinhero.marvel.common.ui.states.FetchState

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