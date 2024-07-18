package com.example.peliculasapp.data

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PeliculasApiService {
/*
    @GET("&s={name}")
    suspend fun getByName(@Path("name") query: String): PeliculasListResponse

    @GET("&t={name}")
    suspend fun getByTitle(@Path("name") query: String): PeliculasListResponse // PeliculasResponseT

    @GET("&i={id}")
    suspend fun getById(@Path("id") id: String): PeliculasResponseT

*/

    @GET("/?apikey=6ea9c0c7")
    suspend fun getByName(@Query("s") query: String): PeliculasListResponse

    @GET("/?apikey=6ea9c0c7")
    suspend fun getByTitle(@Query("t") query: String): PeliculasListResponse // PeliculasResponseT

    @GET("/?apikey=6ea9c0c7")
    suspend fun getById(@Query("i") id: String): PeliculasResponse

}