package com.kotlinhero.marvel.characters.di

import com.kotlinhero.marvel.characters.data.remote.api.CharactersApi
import com.kotlinhero.marvel.characters.data.remote.repositories.CharactersRepositoryImpl
import com.kotlinhero.marvel.characters.domain.repositories.CharactersRepository
import com.kotlinhero.marvel.characters.domain.usecases.GetCharacterUseCase
import com.kotlinhero.marvel.characters.domain.usecases.GetCharactersUseCase
import com.kotlinhero.marvel.characters.domain.usecases.GetComicsUseCase
import com.kotlinhero.marvel.characters.ui.viewmodels.CharacterDetailsViewModel
import com.kotlinhero.marvel.characters.ui.viewmodels.CharactersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val CharactersModule = module {
    factory { CharactersApi(get()) }
    factory { CharactersRepositoryImpl(get()) } bind CharactersRepository::class
    factory { GetCharactersUseCase(get()) }
    factory { GetCharacterUseCase(get()) }
    factory { GetComicsUseCase(get()) }
    viewModel { CharactersViewModel(get()) }
    viewModel { CharacterDetailsViewModel(get(), get(), get()) }
}