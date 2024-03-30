package com.kotlinhero.marvel.features.characters.ui.viewmodels

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlinhero.marvel.core.ui.states.FetchState
import com.kotlinhero.marvel.core.utils.UiText
import com.kotlinhero.marvel.core.utils.evaluate
import com.kotlinhero.marvel.features.characters.domain.entities.Character
import com.kotlinhero.marvel.features.characters.domain.entities.publications.Comic
import com.kotlinhero.marvel.features.characters.domain.entities.publications.Event
import com.kotlinhero.marvel.features.characters.domain.entities.publications.Serie
import com.kotlinhero.marvel.features.characters.domain.entities.publications.Story
import com.kotlinhero.marvel.features.characters.domain.enums.PublicationType
import com.kotlinhero.marvel.features.characters.domain.usecases.GetCharacterUseCase
import com.kotlinhero.marvel.features.characters.domain.usecases.GetComicsUseCase
import com.kotlinhero.marvel.features.characters.domain.usecases.GetEventsUseCase
import com.kotlinhero.marvel.features.characters.domain.usecases.GetSeriesUseCase
import com.kotlinhero.marvel.features.characters.domain.usecases.GetStoriesUseCase
import com.kotlinhero.marvel.features.characters.ui.states.CharacterDetails
import com.kotlinhero.marvel.features.characters.ui.states.CharacterDetailsState
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
        getCharacterDetails()
    }

    fun getCharacterDetails() {
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

    fun onPublicationTypeChange(publicationType: PublicationType) {
        characterDetailsState = characterDetailsState.copy(selectedPublicationType = publicationType)
    }
}