package com.example.cinemashift.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface CinemaApi {

    @GET("/cinema/today")
    suspend fun getFilms(): List<Film>
}
