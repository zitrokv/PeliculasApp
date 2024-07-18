package com.example.peliculasapp.data

import retrofit2.http.GET
import retrofit2.http.Path

interface PeliculasApiService {
    @GET("&t={name}")
    suspend fun getByName(@Path("t") query: String): PeliculasResponse

    @GET("{id}")
    suspend fun getById(@Path("i") id: Int): PeliculasResponse
}