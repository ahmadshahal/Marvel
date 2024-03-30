package com.kotlinhero.marvel.main.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlinhero.marvel.core.ui.states.ChannelAction
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class MainViewModel : ViewModel() {
    var splashScreenVisible = true
        private set

    private val _channel = Channel<ChannelAction>()
    val channel = _channel.receiveAsFlow()

    init {
        removeSplashScreenAfterOneSecond()
    }

    private fun removeSplashScreenAfterOneSecond() {
        viewModelScope.launch {
            delay(1.seconds)
            splashScreenVisible = false
        }
    }
}