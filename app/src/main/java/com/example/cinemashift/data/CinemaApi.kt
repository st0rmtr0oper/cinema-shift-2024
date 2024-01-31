package com.example.cinemashift.data

import retrofit2.http.GET
import retrofit2.http.Path

interface CinemaApi {

    @GET("/cinema/today")
    suspend fun getTodayFilms(): List<Film>

    @GET("/cinema/film/{filmId}")
    suspend fun getCurrentFilm(@Path("filmId") filmId: String): Film
}
