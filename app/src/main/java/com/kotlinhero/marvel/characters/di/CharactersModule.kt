package com.kotlinhero.marvel.characters.di

import com.kotlinhero.marvel.characters.data.remote.api.CharactersApi
import com.kotlinhero.marvel.characters.data.remote.repositories.CharactersRemoteRepository
import com.kotlinhero.marvel.characters.domain.usecases.GetCharactersUseCase
import org.koin.dsl.module

val CharactersModule = module {
    factory { CharactersApi(get()) }
    factory { CharactersRemoteRepository(get()) }
    factory { GetCharactersUseCase(get()) }
}