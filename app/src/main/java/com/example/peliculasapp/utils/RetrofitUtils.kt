package com.example.peliculasapp.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitUtils {
    companion object {
        fun getRetrofit(): Retrofit {

            return Retrofit.Builder()
                .baseUrl("http://www.omdbapi.com/?apikey=6ea9c0c7")
                //.baseUrl("https://superheroapi.com/api/2ba7f57e36c5a4d961aa9ee05639d6d6/")
                //.client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}