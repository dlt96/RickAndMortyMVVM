package com.example.domain.model

import org.threeten.bp.LocalDate

data class CharacterModel (
    val id : Int,
    val name: String,
    val status: String,
    val image: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: String,
    val location: String,
    val dateOfCreation: LocalDate
)