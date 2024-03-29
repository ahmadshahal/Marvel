package com.kotlinhero.marvel.characters.domain.mappers

import com.kotlinhero.marvel.characters.data.remote.models.CharacterDto
import com.kotlinhero.marvel.characters.data.remote.models.ComicDto
import com.kotlinhero.marvel.characters.data.remote.models.EventDto
import com.kotlinhero.marvel.characters.data.remote.models.SerieDto
import com.kotlinhero.marvel.characters.domain.entities.Character
import com.kotlinhero.marvel.characters.domain.entities.Comic
import com.kotlinhero.marvel.characters.domain.entities.Event
import com.kotlinhero.marvel.characters.domain.entities.Serie

private const val LOREM_IPSUM = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."

fun CharacterDto.toCharacter() = Character(
    id = id,
    name = name,
    description = description.ifEmpty { LOREM_IPSUM },
    thumbnail = thumbnail.fullPath,
    age = "30",
    height = "1.80",
    weight = "78",
    location = "Earth 600"
)

fun ComicDto.toComic() = Comic(
    id = id,
    title = title,
    thumbnail = thumbnail.fullPath,
    description = description
)

fun EventDto.toEvent() = Event(
    id = id,
    title = title,
    thumbnail = thumbnail.fullPath,
    description = description
)

fun SerieDto.toSerie() = Serie(
    id = id,
    title = title,
    thumbnail = thumbnail.fullPath,
    description = description
)
