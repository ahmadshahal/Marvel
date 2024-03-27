package com.kotlinhero.marvel.characters.data.mappers

import com.kotlinhero.marvel.characters.data.remote.models.CharacterDto
import com.kotlinhero.marvel.characters.domain.models.Character

fun CharacterDto.toCharacter() = Character(
    id = id,
    name = name,
    thumbnail = thumbnail.path + "." + thumbnail.extension
)