package com.kotlinhero.marvel.characters.domain.entities.publications

class Story(
    id: Int,
    title: String,
    description: String,
    thumbnail: String,
) : Publication(id, title, description, thumbnail)
