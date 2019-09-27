package com.example.domain.model

data class CharacterListModel(
    val characterList: List<CharacterModel>,
    val pageNumber: Int?
)