package com.example.data.model

import com.squareup.moshi.Json

data class CharacterListEntity(
    @Json(name = "info")
    val info: Info,
    @Json(name = "results")
    val results: List<Result>
) {
    data class Result(
        @Json(name = "id")
        val id: Int,
        @Json(name = "name")
        val name: String,
        @Json(name = "status")
        val status: String,
        @Json(name = "species")
        val species: String,
        @Json(name = "type")
        val type: String,
        @Json(name = "gender")
        val gender: String,
        @Json(name = "origin")
        val origin: Origin,
        @Json(name = "location")
        val location: Location,
        @Json(name = "image")
        val image: String,
        @Json(name = "episode")
        val episode: List<String>,
        @Json(name = "url")
        val url: String,
        @Json(name = "created")
        val created: String
    ) {
        data class Location(
            @Json(name = "name")
            val name: String,
            @Json(name = "url")
            val url: String
        )

        data class Origin(
            @Json(name = "name")
            val name: String,
            @Json(name = "url")
            val url: String
        )
    }

    data class Info(
        @Json(name = "count")
        val count: Int,
        @Json(name = "pages")
        val pages: Int,
        @Json(name = "next")
        val next: String,
        @Json(name = "prev")
        val prev: String
    )
}