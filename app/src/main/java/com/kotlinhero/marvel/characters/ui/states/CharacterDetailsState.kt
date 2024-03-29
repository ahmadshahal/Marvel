package com.kotlinhero.marvel.characters.ui.states

import com.kotlinhero.marvel.characters.domain.entities.Character
import com.kotlinhero.marvel.characters.domain.entities.Comic
import com.kotlinhero.marvel.characters.domain.entities.Event
import com.kotlinhero.marvel.characters.domain.entities.Serie
import com.kotlinhero.marvel.characters.domain.enums.ProductType
import com.kotlinhero.marvel.common.ui.states.FetchState

data class CharacterDetailsState(
    val fetchState: FetchState<CharacterDetails> = FetchState.Initial(),
    val selectedProductType: ProductType = ProductType.COMICS,
)

data class CharacterDetails(
    val character: Character,
    val comics: List<Comic>,
    val events: List<Event>,
    val series: List<Serie>
)