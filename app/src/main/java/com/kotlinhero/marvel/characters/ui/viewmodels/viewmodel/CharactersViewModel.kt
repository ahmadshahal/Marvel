package com.kotlinhero.marvel.characters.ui.viewmodels.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kotlinhero.marvel.characters.domain.entities.Character
import com.kotlinhero.marvel.characters.domain.usecases.GetCharactersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    private val _charactersState: MutableStateFlow<PagingData<Character>> = MutableStateFlow(value = PagingData.empty())
    val charactersState: StateFlow<PagingData<Character>> get() = _charactersState.asStateFlow()

    init {
        getCharacters()
    }

    private fun getCharacters() {
        viewModelScope.launch {
            getCharactersUseCase()
                .distinctUntilChanged()
                .cachedIn(viewModelScope)
                .collect {
                    _charactersState.emit(it)
                }
        }
    }
}