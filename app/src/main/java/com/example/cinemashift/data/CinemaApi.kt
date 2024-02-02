package com.example.cinemashift.data

import com.google.gson.JsonObject
import retrofit2.http.GET
import retrofit2.http.Path

interface CinemaApi {

    @GET("/cinema/today")
    suspend fun getTodayFilms(): FilmsResponse

    @GET("/cinema/film/{filmId}")
    suspend fun getFilmByID(@Path("filmId") filmId: Long): FilmResponse

    @GET("/cinema/film/{filmId}/schedule")
    suspend fun getFilmSchedule(@Path("filmId") filmId: Long): ScheduleResponse
}
