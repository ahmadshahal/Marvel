package com.kotlinhero.marvel.characters.ui.states

import com.kotlinhero.marvel.characters.domain.enums.ProductType
import com.kotlinhero.marvel.common.ui.states.FetchState

data class CharacterDetailsState(
    val fetchState: FetchState<List<*>> = FetchState.Initial(),
    val selectedProductType: ProductType = ProductType.COMICS,
)
