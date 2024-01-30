package com.example.cinemashift.data

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private const val BASE_URL = "https://shift-backend.onrender.com/"

private val retrofit =
    Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(
        BASE_URL
    ).build()

interface CinemaApiService {
    @GET("")
    /*suspend*/ fun getFilms(): Call<List<Film>>    //--coroutines???
}

object cinemaApi {
    val retrofitService: CinemaApiService by lazy { retrofit.create(CinemaApiService::class.java) }
}