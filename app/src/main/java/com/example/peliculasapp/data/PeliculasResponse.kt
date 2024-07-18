package com.example.peliculasapp.data

import com.google.gson.annotations.SerializedName

data class PeliculasResponse(
    @SerializedName("Response") val response: Boolean,
    @SerializedName("imdbID") val id: String,

    @SerializedName("Title") val titulo: String,
    @SerializedName("Year") val anyo: Int,
    @SerializedName("Poster") val poster: String,
    @SerializedName("Plot") val sinopsis: String,
    @SerializedName("Runtime") val duracion: String,
    @SerializedName("Director") val director: String,
    @SerializedName("Genre") val genero: String,
    @SerializedName("Country") val pais: String


) {
}