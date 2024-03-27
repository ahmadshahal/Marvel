package com.kotlinhero.marvel.characters.data.remote.repositories

import com.kotlinhero.marvel.characters.data.remote.api.CharactersApi
import com.kotlinhero.marvel.common.repositories.BaseRemoteRepository

class CharactersRemoteRepository(
    private val charactersApi: CharactersApi
) : BaseRemoteRepository() {

}