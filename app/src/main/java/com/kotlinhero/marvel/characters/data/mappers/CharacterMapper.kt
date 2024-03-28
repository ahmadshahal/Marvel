package com.kotlinhero.marvel.characters.data.mappers

import com.kotlinhero.marvel.characters.data.remote.models.CharacterDto
import com.kotlinhero.marvel.characters.domain.entities.Character

fun CharacterDto.toCharacter() = Character(
    id = id,
    name = name,
    description = description.ifEmpty { LOREM_IPSUM },
    thumbnail = thumbnail.path + "." + thumbnail.extension,
    age = "30",
    height = "1.80",
    weight = "78",
    location = "Earth 600"
)

private const val LOREM_IPSUM = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."