package com.example.cinemashift.data

import com.google.gson.JsonObject
import retrofit2.http.GET

interface CinemaApi {

    @GET("/cinema/today")
    suspend fun getResponse(): JsonObject

    @GET("/cinema/today")
    suspend fun getTodayFilms(): FilmsResponse

//    @GET("/cinema/film/{filmId}")
//    suspend fun getCurrentFilm(@Path("filmId") filmId: String): Film
}
