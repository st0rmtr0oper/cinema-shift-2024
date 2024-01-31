package com.example.cinemashift.data

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class CinemaRepository {
    companion object {
        const val BASE_URL = "https://shift-backend.onrender.com/"
        const val CROP_URL = "https://shift-backend.onrender.com"
    }


    private val gson = GsonBuilder().create()

    private val retrofit = Retrofit.Builder()
        .client(provideOkHttpClient())
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()


    private fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient().newBuilder().connectTimeout(10L, TimeUnit.SECONDS)
            .writeTimeout(10L, TimeUnit.SECONDS).readTimeout(10L, TimeUnit.SECONDS).build()

    private val cinemaApi by lazy {
        retrofit.create(CinemaApi::class.java)
    }

    suspend fun getTodayFilms(): List<Film> = cinemaApi.getTodayFilms().films

    suspend fun getResponse(): String = cinemaApi.getResponse().toString()

//    suspend fun getCurrentFilm():Film = cinemaApi.getCurrentFilm( ... )      //TODO вторая функция
}