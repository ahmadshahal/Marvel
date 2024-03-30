package com.kotlinhero.marvel.core.ui.states

import androidx.navigation.NavController
import com.kotlinhero.marvel.core.utils.UiText

sealed class ChannelAction {
    class Navigate(val navigationAction: (NavController) -> Unit) : ChannelAction()
    class ShowSnackBar(val uiText: UiText) : ChannelAction()
}