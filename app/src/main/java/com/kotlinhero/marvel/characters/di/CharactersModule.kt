package com.kotlinhero.marvel.characters.di

import com.kotlinhero.marvel.characters.data.remote.api.CharactersApi
import com.kotlinhero.marvel.characters.data.remote.repositories.CharactersRemoteRepository
import com.kotlinhero.marvel.characters.domain.usecases.GetCharactersUseCase
import com.kotlinhero.marvel.characters.ui.viewmodels.viewmodel.CharactersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val CharactersModule = module {
    factory { CharactersApi(get()) }
    factory { CharactersRemoteRepository(get()) }
    factory { GetCharactersUseCase(get()) }
    viewModel { CharactersViewModel(get()) }
}