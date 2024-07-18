package com.example.peliculasapp.adapters

import com.example.peliculasapp.data.PeliculasResponse

class PeliculasProvider {
    companion object{

        fun findAll() : List<PeliculasResponse>{
            return emptyList()
        }

        fun findInitial() : List<PeliculasResponse>{
            return emptyList()
        }

        var textoBuscar : String = "Terminator"
    }
}