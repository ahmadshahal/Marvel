package com.kotlinhero.marvel.common.ui.states

import androidx.navigation.NavController
import com.kotlinhero.marvel.common.utils.UiText

sealed class ChannelAction {
    class Navigate(val navigationAction: (NavController) -> Unit) : ChannelAction()
    class ShowSnackBar(val uiText: UiText) : ChannelAction()
}