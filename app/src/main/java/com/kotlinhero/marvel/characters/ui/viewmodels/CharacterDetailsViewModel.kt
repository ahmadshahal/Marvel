package com.kotlinhero.marvel.characters.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlinhero.marvel.characters.domain.entities.Character
import com.kotlinhero.marvel.characters.domain.usecases.GetCharacterUseCase
import com.kotlinhero.marvel.common.ui.states.FetchState
import com.kotlinhero.marvel.common.utils.UiText
import kotlinx.coroutines.launch

class CharacterDetailsViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getCharacterUseCase: GetCharacterUseCase,
) : ViewModel() {

    private val id = savedStateHandle.get<Int>("id") ?: 0

    var characterFetchState by mutableStateOf<FetchState<Character>>(FetchState.Initial())
        private set

    init {
        getCharacter()
    }

    fun getCharacter() {
        viewModelScope.launch {
            characterFetchState = FetchState.Loading()
            getCharacterUseCase(id).fold(
                onSuccess = {
                    characterFetchState = FetchState.Success(it)
                },
                onFailure = {
                    characterFetchState =
                        FetchState.Error(UiText.DynamicString(it.localizedMessage ?: ""))
                }
            )
        }
    }
}