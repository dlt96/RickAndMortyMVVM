package com.example.presentation.mapper

import com.example.domain.model.CharacterListModel
import com.example.domain.model.CharacterModel
import com.example.presentation.model.CharacterDisplayModel
import com.example.presentation.model.ListItemDisplayModel
import com.example.presentation.model.LoadingDisplayModel
import org.threeten.bp.LocalDate

object CharacterDisplayMapper {
    fun transform(model: CharacterListModel): List<ListItemDisplayModel> =
        model.characterList.map {
            transformCharacter(it)
        }.toMutableList()
            .apply {
                if (model.nextPage != null) {
                    add(LoadingDisplayModel)
                }
            }

    private fun transformCharacter(model: CharacterModel) : ListItemDisplayModel  =
        CharacterDisplayModel(
            id = model.id,
            name = model.name,
            status = model.status,
            image = model.image,
            species = model.species,
            gender = model.gender,
            origin = model.origin,
            location = model.location,
            dateOfCreation = transformDate(model.dateOfCreation)
        )

    private fun transformDate(date: LocalDate) =
        date.month.toString() + "/" + date.year.toString()
}