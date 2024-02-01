package com.example.cinemashift.data

import com.squareup.moshi.JsonClass

//remember to create new data class fo each rest api

data class ScheduleResponse(
    val success: String,
    val reason: String?,
    val schedules: List<Schedule>
)
data class FilmsResponse(
    val success: String,
    val reason: String?,
    val films: List<Film>
)

data class FilmResponse(
    val success: String,
    val reason: String?,
    val film: Film
)

data class Schedule(
    val date: String,
    val seances: List<ScheduleSeance>
)

data class ScheduleSeance(
    val time: List<String>,
    val hall: Hall,
    val payedTickets: List<Ticket>
)

data class Hall(
    val name: String,
    val places: List<Place>
)

data class Place(
    val price: Int,
    val type: Type
)

data class Ticket(
    val filmId: String,
    val row: Int,
    val column: Int,
    val seance: ScheduleSeance,
    val phone: String
)

data class Film(
    val id: String,
    val name: String,
    val originalName: String,
    val description: String,
    val releaseDate: String,
    val actors: List<FilmPerson>,
    val directors: List<FilmPerson>,
    val runtime: Int,
    val ageRating: AgeRating,
    val genres: List<String>,
    val userRatings: Rating,
    val img: String,
    val country: Country
)

data class FilmPerson(
    val id: String,
    val professions: List<Professions>,
    val fullName: String
)

data class Country(
    val name: String,
    val code: String,
    val code2: String,
    val id: Int
)

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

enum class Type {
    ECONOM, COMFORT, BLOCKED
}