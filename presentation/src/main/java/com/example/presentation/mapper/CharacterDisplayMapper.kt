package com.example.presentation.mapper

import com.example.domain.model.CharacterListModel
import com.example.presentation.model.CharacterDisplayModel
import org.threeten.bp.LocalDate

object CharacterDisplayMapper {
    fun transform(model: CharacterListModel): List<CharacterDisplayModel> =
        model.characterList.map {
            CharacterDisplayModel(
                id = it.id,
                name = it.name,
                status = it.status,
                image = it.image,
                species = it.species,
                gender = it.gender,
                origin = it.origin,
                location = it.location,
                dateOfCreation = transformDate(it.dateOfCreation)
            )
        }

    private fun transformDate(date: LocalDate) =
        date.month.toString() + "/" + date.year.toString()
}