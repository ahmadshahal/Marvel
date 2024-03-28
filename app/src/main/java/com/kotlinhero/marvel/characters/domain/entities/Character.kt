package com.kotlinhero.marvel.characters.domain.entities

data class Character(
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
    val thumbnail: String = "",
    val age: String = "",
    val weight: String = "",
    val height: String = "",
    val location: String = "",
)
