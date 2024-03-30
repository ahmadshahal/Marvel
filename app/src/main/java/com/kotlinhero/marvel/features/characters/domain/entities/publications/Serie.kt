package com.kotlinhero.marvel.features.characters.domain.entities.publications

class Serie(
    id: Int,
    title: String,
    description: String,
    thumbnail: String,
) : Publication(id, title, description, thumbnail)
