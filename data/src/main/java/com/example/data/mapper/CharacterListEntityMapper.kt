package com.example.data.mapper

import com.example.data.model.CharacterListEntity
import com.example.data.utils.DateUtils.stringToDate
import com.example.domain.model.CharacterListModel
import com.example.domain.model.CharacterModel
import java.util.stream.Collectors

object CharacterListEntityMapper {

    fun transform(entity: CharacterListEntity) =
        CharacterListModel(
            transformCharacterList(entity.results),
            getNextPageNumber(entity.info.next)
        )

    private fun getNextPageNumber(next: String?): Int? =
        next?.split('=')?.get(1)?.toInt()


    private fun transformCharacterList(results: List<CharacterListEntity.Result>): List<CharacterModel> {
        return results.stream().map {
            CharacterModel(
                it.id,
                it.name,
                it.status,
                it.image,
                it.species,
                it.type,
                it.gender,
                it.origin.name,
                it.location.name,
                stringToDate(it.created)
            )
        }.collect(Collectors.toList())
    }
}