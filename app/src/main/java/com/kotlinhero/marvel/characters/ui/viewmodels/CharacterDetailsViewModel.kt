package com.kotlinhero.marvel.characters.ui.viewmodels

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlinhero.marvel.characters.domain.usecases.GetCharacterUseCase
import com.kotlinhero.marvel.characters.domain.usecases.GetComicsUseCase
import com.kotlinhero.marvel.characters.ui.states.CharacterDetailsState
import com.kotlinhero.marvel.common.ui.states.FetchState
import com.kotlinhero.marvel.common.utils.UiText
import kotlinx.coroutines.launch

class CharacterDetailsViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getCharacterUseCase: GetCharacterUseCase,
    private val getComicsUseCase: GetComicsUseCase,
) : ViewModel() {

    private val id = savedStateHandle.get<Int>("id") ?: 0

    var characterDetailsState by mutableStateOf(CharacterDetailsState())
        private set

    val totalFetchState by derivedStateOf {
        when {
            characterDetailsState.characterFetchState is FetchState.Loading
                    || characterDetailsState.comicsFetchState is FetchState.Loading -> FetchState.Loading<Unit>()

            characterDetailsState.characterFetchState is FetchState.Error -> {
                val errorState = characterDetailsState.characterFetchState as FetchState.Error
                FetchState.Error(errorState.message)
            }

            characterDetailsState.comicsFetchState is FetchState.Error -> {
                val errorState = characterDetailsState.comicsFetchState as FetchState.Error
                FetchState.Error(errorState.message)
            }

            characterDetailsState.characterFetchState is FetchState.Success
                    && characterDetailsState.comicsFetchState is FetchState.Success ->
                FetchState.Success(Unit)

            else -> FetchState.Initial()
        }
    }

    init {
        getCharacter()
        getComics()
    }

    fun refresh() {
        getCharacter()
        getComics()
    }

    private fun getCharacter() {
        viewModelScope.launch {
            characterDetailsState = characterDetailsState.characterFetchStateCopy(
                FetchState.Loading()
            )
            getCharacterUseCase(id).fold(
                onSuccess = {
                    characterDetailsState = characterDetailsState.characterFetchStateCopy(
                        FetchState.Success(it)
                    )
                },
                onFailure = {
                    characterDetailsState = characterDetailsState.characterFetchStateCopy(
                        FetchState.Error(UiText.DynamicString(it.localizedMessage ?: ""))
                    )
                }
            )
        }
    }

    private fun getComics() {
        viewModelScope.launch {
            characterDetailsState = characterDetailsState.comicsFetchStateCopy(
                FetchState.Loading()
            )
            getComicsUseCase(id).fold(
                onSuccess = {
                    characterDetailsState = characterDetailsState.comicsFetchStateCopy(
                        FetchState.Success(it)
                    )
                },
                onFailure = {
                    characterDetailsState = characterDetailsState.comicsFetchStateCopy(
                        FetchState.Error(UiText.DynamicString(it.localizedMessage ?: ""))
                    )
                }
            )
        }
    }
}