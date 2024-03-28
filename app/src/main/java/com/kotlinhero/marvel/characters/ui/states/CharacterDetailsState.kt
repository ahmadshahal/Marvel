package com.kotlinhero.marvel.characters.ui.states

import com.kotlinhero.marvel.characters.domain.entities.Character
import com.kotlinhero.marvel.characters.domain.entities.Comic
import com.kotlinhero.marvel.common.ui.states.FetchState

data class CharacterDetailsState(
    val characterFetchState: FetchState<Character> = FetchState.Initial(),
    val comicsFetchState: FetchState<List<Comic>> = FetchState.Initial()
) {
    fun characterFetchStateCopy(fetchState: FetchState<Character>) = copy(
        characterFetchState = fetchState
    )

    fun comicsFetchStateCopy(fetchState: FetchState<List<Comic>>) = copy(
        comicsFetchState = fetchState
    )
}
