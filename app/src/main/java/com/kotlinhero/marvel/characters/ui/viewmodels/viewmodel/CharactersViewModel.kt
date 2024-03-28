package com.kotlinhero.marvel.characters.ui.viewmodels.viewmodel

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlinhero.marvel.characters.domain.entities.Character
import com.kotlinhero.marvel.characters.domain.usecases.GetCharactersUseCase
import com.kotlinhero.marvel.common.ui.states.FetchState
import com.kotlinhero.marvel.common.utils.UiText
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {
    var fetchState by mutableStateOf<FetchState<List<Character>>>(FetchState.Initial())
        private set

    val characters by derivedStateOf { fetchState.data ?: emptyList() }

    init {
        getCharacters()
    }

    fun getCharacters() {
        viewModelScope.launch {
            fetchState = FetchState.Loading()
            getCharactersUseCase().fold(
                onSuccess = {
                    fetchState = FetchState.Success(it)
                },
                onFailure = {
                    fetchState = FetchState.Error(UiText.DynamicString(it.localizedMessage ?: ""))
                }
            )
        }
    }
}