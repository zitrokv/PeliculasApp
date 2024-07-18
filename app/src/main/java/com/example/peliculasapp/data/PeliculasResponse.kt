package com.example.peliculasapp.data

import com.google.gson.annotations.SerializedName

data class PeliculasListResponse(
    @SerializedName("Response") val response:String,
    @SerializedName("totalResults") val resultsFor: Int,
    @SerializedName("Search") val results: List<PeliculasResponse> = emptyList()
){

}
data class PeliculasResponse(
    @SerializedName("Title") val titulo: String,
    @SerializedName("Year") val anyo: String,
    @SerializedName("imdbID") val id: String,
    @SerializedName("Type") val tipo: String,
    @SerializedName("Poster") val poster: String,
    @SerializedName("Plot") val sinopsis: String?,
    @SerializedName("Runtime") val duracion: String?,
    @SerializedName("Director") val director: String?,
    @SerializedName("Genre") val genero: String?,
    @SerializedName("Country") val pais: String?
)