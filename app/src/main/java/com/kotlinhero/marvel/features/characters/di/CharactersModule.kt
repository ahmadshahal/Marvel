package com.kotlinhero.marvel.features.characters.di

import com.kotlinhero.marvel.features.characters.data.remote.api.CharactersApi
import com.kotlinhero.marvel.features.characters.data.remote.repositories.CharactersRepositoryImpl
import com.kotlinhero.marvel.features.characters.domain.repositories.CharactersRepository
import com.kotlinhero.marvel.features.characters.domain.usecases.GetCharacterUseCase
import com.kotlinhero.marvel.features.characters.domain.usecases.GetCharactersUseCase
import com.kotlinhero.marvel.features.characters.domain.usecases.GetComicsUseCase
import com.kotlinhero.marvel.features.characters.domain.usecases.GetEventsUseCase
import com.kotlinhero.marvel.features.characters.domain.usecases.GetSeriesUseCase
import com.kotlinhero.marvel.features.characters.domain.usecases.GetStoriesUseCase
import com.kotlinhero.marvel.features.characters.ui.viewmodels.CharacterDetailsViewModel
import com.kotlinhero.marvel.features.characters.ui.viewmodels.CharactersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val CharactersModule = module {
    factory { CharactersApi(get()) }
    factory { CharactersRepositoryImpl(get()) } bind CharactersRepository::class
    factory { GetCharactersUseCase(get()) }
    factory { GetCharacterUseCase(get()) }
    factory { GetComicsUseCase(get()) }
    factory { GetEventsUseCase(get()) }
    factory { GetSeriesUseCase(get()) }
    factory { GetStoriesUseCase(get()) }
    viewModel { CharactersViewModel(get()) }
    viewModel { CharacterDetailsViewModel(get(), get(), get(), get(), get(), get()) }
}