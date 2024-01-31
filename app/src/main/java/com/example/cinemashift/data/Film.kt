package com.example.cinemashift.data

//TODO: use just strings without enums???
//TODO: maybe i can use other data types?
//кастомные десериализации вроде не нужны...

data class Film(
    val id: String,
    val name: String,
    val originalName: String,
    val description: String,
    val releaseDate: String,
    val filmPeople: ArrayList<FilmPerson>,
    val directors: ArrayList<FilmPerson>,
    val runtime: Int,
    val ageRating: AgeRating,
    val genres: ArrayList<String>,
    val userRatings: Rating,
    val img: String,
    val country: Country
)

data class FilmPerson (
    val id: String,
    val professions: Professions,
    val fullName: String
)

data class Country (
    val name: String,
    val code: String,
    val code2: String,
    val id: Int
)

data class Rating (
    val kinopoisk: String,
    val imdb: String
)

enum class AgeRating {        //println("ageRating: ${AgeRating.name})
    G, PG, PG13, R, NC17
}

enum class Professions {
    ACTOR, DIRECTOR
}