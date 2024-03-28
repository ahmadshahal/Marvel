package com.kotlinhero.marvel.characters.data.mappers

import com.kotlinhero.marvel.characters.data.remote.models.CharacterDto
import com.kotlinhero.marvel.characters.domain.entities.Character

fun CharacterDto.toCharacter() = Character(
    id = id,
    name = name,
    description = description,
    thumbnail = thumbnail.path + "." + thumbnail.extension
)