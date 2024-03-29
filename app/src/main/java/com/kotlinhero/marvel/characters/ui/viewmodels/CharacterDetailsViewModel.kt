package com.kotlinhero.marvel.characters.ui.viewmodels

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlinhero.marvel.characters.domain.entities.Character
import com.kotlinhero.marvel.characters.domain.entities.Comic
import com.kotlinhero.marvel.characters.domain.usecases.GetCharacterUseCase
import com.kotlinhero.marvel.characters.domain.usecases.GetComicsUseCase
import com.kotlinhero.marvel.characters.ui.states.CharacterDetailsState
import com.kotlinhero.marvel.common.ui.states.FetchState
import com.kotlinhero.marvel.common.utils.UiText
import com.kotlinhero.marvel.common.utils.evaluate
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

class CharacterDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val getCharacterUseCase: GetCharacterUseCase,
    private val getComicsUseCase: GetComicsUseCase,
) : ViewModel() {

    private val id = savedStateHandle.get<Int>("id") ?: 0

    var characterDetailsState by mutableStateOf(CharacterDetailsState())
        private set

    val character by derivedStateOf {
        // TODO: Find a solution for the type safety
        characterDetailsState.fetchState.data?.get(0) as? Character ?: Character()
    }

    val comics by derivedStateOf {
        // TODO: Find a solution for the type safety
        characterDetailsState.fetchState.data?.get(1) as? List<Comic> ?: emptyList()
    }

    init {
        getData()
    }

    fun refresh() = getData()

    private fun getData() {
        viewModelScope.launch {
            characterDetailsState =
                characterDetailsState.copy(fetchState = FetchState.Loading())
            val character = async { getCharacterUseCase(id) }
            val comics = async { getComicsUseCase(id) }
            val results = awaitAll(character, comics)
            val evaluatedResult = results.evaluate()
            evaluatedResult.fold(
                onSuccess = {
                    characterDetailsState = characterDetailsState.copy(
                        fetchState = FetchState.Success(it)
                    )
                },
                onFailure = {
                    characterDetailsState = characterDetailsState.copy(
                        fetchState = FetchState.Error(
                            UiText.DynamicString(
                                it.localizedMessage ?: ""
                            )
                        )
                    )
                }
            )
        }
    }
}