package com.kotlinhero.marvel.characters.domain.entities.publications

class Comic(
    id: Int,
    title: String,
    description: String,
    thumbnail: String,
) : Publication(id, title, description, thumbnail)
