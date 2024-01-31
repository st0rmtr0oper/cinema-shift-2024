package com.example.cinemashift.data

import com.squareup.moshi.JsonClass

//TODO: use just strings without enums???
//TODO: maybe i can use other data types?
//кастомные десериализации вроде не нужны...

@JsonClass(generateAdapter = true)
data class FilmsResponse(
    val success: String,
    val reason: String?,
    val films: List<Film>
)

@JsonClass(generateAdapter = true)
data class Film(
    val id: String,
    val name: String,
    val originalName: String,
    val description: String,
    val releaseDate: String,
    val actors: ArrayList<FilmPerson>,
    val directors: ArrayList<FilmPerson>,
    val runtime: Int,
    val ageRating: AgeRating,
    val genres: ArrayList<String>,
    val userRatings: Rating,
    val img: String,
    val country: Country
)

@JsonClass(generateAdapter = true)
data class FilmPerson(
    val id: String,
    val professions: Professions,
    val fullName: String
)

@JsonClass(generateAdapter = true)
data class Country(
    val name: String,
    val code: String,
    val code2: String,
    val id: Int
)

@JsonClass(generateAdapter = true)
data class Rating(
    val kinopoisk: String,
    val imdb: String
)

enum class AgeRating {        //println("ageRating: ${AgeRating.name})
    G, PG, PG13, R, NC17
}

enum class Professions {
    ACTOR, DIRECTOR
}