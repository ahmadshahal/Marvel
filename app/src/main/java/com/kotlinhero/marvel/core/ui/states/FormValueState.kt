package com.kotlinhero.marvel.core.ui.states

import com.kotlinhero.marvel.core.utils.UiText

data class FormValueState<T>(
    val value: T,
    val isValid: Boolean = true,
    val validationMessage: UiText? = null,
)
