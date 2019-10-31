package com.example.presentation.model

const val HomeItemViewType = 0
const val HomeLoadingViewType = 1

sealed class ListItemDisplayModel {
    abstract fun isLoadingType(): Int
    abstract fun areItemsTheSame(other: ListItemDisplayModel): Boolean
    abstract fun areContentsTheSame(other: ListItemDisplayModel): Boolean
}

data class CharacterDisplayModel (
    val id : Int,
    val name: String,
    val status: String,
    val image: String,
    val species: String,
    val gender: String,
    val origin: String,
    val location: String,
    val dateOfCreation: String
) : ListItemDisplayModel() {
    override fun isLoadingType() = HomeItemViewType

    override fun areItemsTheSame(other: ListItemDisplayModel) =
        other is CharacterDisplayModel && id == other.id


    override fun areContentsTheSame(other: ListItemDisplayModel) =
        other == this

    fun toDetailDisplay() = CharacterDetailDisplay(
        id = id,
        name = name,
        image = image,
        type = species,
        planet = location
    )
}

object LoadingDisplayModel : ListItemDisplayModel() {
    override fun isLoadingType() = HomeLoadingViewType

    override fun areItemsTheSame(other: ListItemDisplayModel) =
        other is CharacterDisplayModel

    override fun areContentsTheSame(other: ListItemDisplayModel) =
        equals(other)
}