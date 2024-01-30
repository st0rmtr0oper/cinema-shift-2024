package com.example.cinemashift.ui.poster

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cinemashift.data.Film
import com.example.cinemashift.data.cinemaApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PosterViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

//    init {
//         getFilmsJsonString()
//    }
//
//    private fun getFilmsJsonString() {
//        cinemaApi.retrofitService.getFilms().enqueue(object: Callback<List<Film>> {
//            override fun onFailure(call: Call<List<Film>>, t: Throwable) {
//                _text.value = "Fail"
//            }
//
//            override fun onResponse(call: Call<List<Film>>, response: Response<List<Film>>) {
//                _text.value = response.body().toString()
//            }
//        })
//
//    }
//
//    private val _response = MutableLiveData<String>()
//    val response: LiveData<String> = _response
//
//    init {
//        getMarsRealEstateProperties()     //TODO: coroutines
//    }
//
//    private fun getMarsRealEstateProperties() {
//        cinemaApi.retrofitService.getFilms().enqueue(object: Callback<List<Film>> {
//            override fun onResponse(
//                call: Call<List<Film>>,
//                response: Response<List<Film>>
//            ) {
//                _response.value = "Success: ${response.body()?.size} Mars properties retrieved"
//            }
//
//            override fun onFailure(call: Call<List<Film>>, t: Throwable) {
//                _response.value = "Failure: ${t.message}"
//            }
//        })
//    }
}