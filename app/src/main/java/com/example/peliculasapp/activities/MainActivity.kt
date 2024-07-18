package com.example.peliculasapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.peliculasapp.R
import com.example.peliculasapp.adapters.PeliculasAdapter
import com.example.peliculasapp.adapters.PeliculasProvider
import com.example.peliculasapp.data.PeliculasApiService
import com.example.peliculasapp.data.PeliculasResponse
import com.example.peliculasapp.databinding.ActivityMainBinding
import com.example.peliculasapp.utils.RetrofitUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var adapter: PeliculasAdapter
    lateinit var peliculasList: List<PeliculasResponse>

    override fun onCreate(savedInstanceState: Bundle?) {
        //super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //adapter = PeliculasAdapter(peliculasList)
        peliculasList = emptyList()
        adapter = PeliculasAdapter(peliculasList){ opcionClick -> verDetalle( peliculasList[opcionClick])}

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)


        searchByName(PeliculasProvider.textoBuscar)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        //var inflater:MenuInflater = menuInflater
        //inflater.inflate(R.menu.menu_activity_main, menu)

        menuInflater.inflate(R.menu.menu_activity_main, menu)


        val searchViewItem = menu.findItem(R.id.buscar)
        val searchView = searchViewItem.actionView as androidx.appcompat.widget.SearchView
        searchView.setOnQueryTextListener(object: androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query.isNullOrEmpty()) {
                    searchByName(PeliculasProvider.textoBuscar)
                } else {
                    PeliculasProvider.textoBuscar = query
                    searchByName(query)
                }

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        return true
        //return super.onCreateOptionsMenu(menu)
    }

    //s
    private fun searchByName(query: String){
        //segundo plano ó hilo secundario paralelo
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val apiService = RetrofitUtils.getRetrofit().create(PeliculasApiService::class.java)
                val  result = apiService.getByName(query)

                runOnUiThread {
                    if (result.response == "True") {
                        //peliculasList = mutableListOf<PeliculasResponse>( result )
                        peliculasList = result.results
                    } else {
                        peliculasList = emptyList()
                    }
                    adapter.updateData(peliculasList, query)
                }

            }catch (e: Exception){
                e.printStackTrace()
            }
        }

    }


    /*private fun searchByTitle(query: String){
        //segundo plano ó hilo secundario paralelo
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val apiService = RetrofitUtils.getRetrofit().create(PeliculasApiService::class.java)
                val  result = apiService.getByTitle(query)

                runOnUiThread {
                    if (result.response) {
                        //peliculasList = mutableListOf<PeliculasResponse>( result )
                        peliculasList = result
                        adapter.updateData(peliculasList, query)
                    } else {
                        adapter.updateData(PeliculasProvider.EmptyList(), query)
                    }
                }

            }catch (e: Exception){
                e.printStackTrace()
            }
        }

    }*/

    fun verDetalle(peliculaDetalle: PeliculasResponse){
        val intent : Intent = Intent(this, DetalleActivity::class.java) //::class.java
        intent.putExtra("FILM_ID", peliculaDetalle.id)
        startActivity(intent)

    }
}