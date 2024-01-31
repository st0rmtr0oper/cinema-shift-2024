package com.example.cinemashift.data

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
class CinemaRepository {
    companion object {
        const val BASE_URL = "https://shift-backend.onrender.com/"
    }

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    private val retrofit =
        Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(
            BASE_URL
        ).build()

    //пока не знаю где и зачем это чудо использовать
    private fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient().newBuilder().connectTimeout(10L, TimeUnit.SECONDS)
            .writeTimeout(10L, TimeUnit.SECONDS).readTimeout(10L, TimeUnit.SECONDS).build()

    private val cinemaApi by lazy {
        retrofit.create(CinemaApi::class.java)
    }

    fun test() {}
    public final suspend fun getTodayFilms():List<Film> = cinemaApi.getTodayFilms().films

//    suspend fun getCurrentFilm():Film = cinemaApi.getCurrentFilm( ... )      //TODO вторая функция
}