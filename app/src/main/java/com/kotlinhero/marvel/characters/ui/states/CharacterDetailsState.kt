package com.kotlinhero.marvel.characters.ui.states

import com.kotlinhero.marvel.common.ui.states.FetchState

data class CharacterDetailsState(
    val fetchState: FetchState<List<*>> = FetchState.Initial(),
)
