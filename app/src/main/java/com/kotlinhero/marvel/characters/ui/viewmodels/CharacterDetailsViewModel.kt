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
import com.kotlinhero.marvel.characters.domain.entities.Event
import com.kotlinhero.marvel.characters.domain.entities.Serie
import com.kotlinhero.marvel.characters.domain.entities.Story
import com.kotlinhero.marvel.characters.domain.enums.ProductType
import com.kotlinhero.marvel.characters.domain.usecases.GetCharacterUseCase
import com.kotlinhero.marvel.characters.domain.usecases.GetComicsUseCase
import com.kotlinhero.marvel.characters.domain.usecases.GetEventsUseCase
import com.kotlinhero.marvel.characters.domain.usecases.GetSeriesUseCase
import com.kotlinhero.marvel.characters.domain.usecases.GetStoriesUseCase
import com.kotlinhero.marvel.characters.ui.states.CharacterDetails
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
    private val getEventsUseCase: GetEventsUseCase,
    private val getSeriesUseCase: GetSeriesUseCase,
    private val getStoriesUseCase: GetStoriesUseCase,
) : ViewModel() {

    private val id = savedStateHandle.get<Int>("id") ?: 0

    var characterDetailsState by mutableStateOf(CharacterDetailsState())
        private set

    val character by derivedStateOf { characterDetailsState.fetchState.data?.character ?: Character() }

    val comics by derivedStateOf { characterDetailsState.fetchState.data?.comics ?: emptyList() }

    val events by derivedStateOf { characterDetailsState.fetchState.data?.events ?: emptyList() }

    val series by derivedStateOf { characterDetailsState.fetchState.data?.series ?: emptyList() }

    val stories by derivedStateOf { characterDetailsState.fetchState.data?.stories ?: emptyList() }

    init {
        getData()
    }

    fun getData() {
        viewModelScope.launch {
            characterDetailsState = characterDetailsState.copy(fetchState = FetchState.Loading())
            val character = async { getCharacterUseCase(id) }
            val comics = async { getComicsUseCase(id) }
            val events = async { getEventsUseCase(id) }
            val series = async { getSeriesUseCase(id) }
            val stories = async { getStoriesUseCase(id) }
            val results = awaitAll(character, comics, events, series, stories)
            val evaluatedResult = results.evaluate()
            evaluatedResult.fold(
                onSuccess = {
                    val characterDetails = CharacterDetails(
                        character = it[0] as Character,
                        comics = it[1] as List<Comic>,
                        events = it[2] as List<Event>,
                        series = it[3] as List<Serie>,
                        stories = it[4] as List<Story>
                    )
                    characterDetailsState = characterDetailsState.copy(
                        fetchState = FetchState.Success(characterDetails)
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

    fun onProductTypeChange(productType: ProductType) {
        characterDetailsState = characterDetailsState.copy(selectedProductType = productType)
    }
}