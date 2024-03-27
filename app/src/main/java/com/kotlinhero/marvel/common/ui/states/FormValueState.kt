package com.kotlinhero.marvel.common.ui.states

import com.kotlinhero.marvel.common.utils.UiText

data class FormValueState<T>(
    val value: T,
    val isValid: Boolean = true,
    val validationMessage: UiText? = null,
)
