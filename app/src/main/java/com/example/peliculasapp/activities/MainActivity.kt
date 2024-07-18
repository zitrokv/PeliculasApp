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
    var peliculasList = emptyList<PeliculasResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        //super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        peliculasList= PeliculasProvider.findAll()
        adapter = PeliculasAdapter(peliculasList)
        //adapter = PeliculasAdapter(peliculasList){ opcionClick -> verDetalle( peliculasList[opcionClick])}

        //searchByName(PeliculasProvider.textoBuscar)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)


        searchByName(PeliculasProvider.textoBuscar)
    }

    override fun onResume() {
        super.onResume()
        adapter.updateData(PeliculasProvider.findAll(), "")
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
                adapter.updateData(PeliculasProvider.findAll(),"")

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        return true
        //return super.onCreateOptionsMenu(menu)
    }

    private fun searchByName(query: String){
        //segundo plano ó hilo secundario paralelo
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val apiService = RetrofitUtils.getRetrofit().create(PeliculasApiService::class.java)
                val result = apiService.getByName(query)

                runOnUiThread {
                    if (result.response) {
                        peliculasList = mutableListOf<PeliculasResponse>( result )
                        adapter.updateData(peliculasList, query)
                    } else {
                        adapter.updateData(emptyList(), query)
                    }
                }

            }catch (e: Exception){
                e.printStackTrace()
            }
        }

    }

    fun verDetalle(superheroDetalle: PeliculasResponse){
        //Log.i("verTraza", getString(superheroDetalle.name))
        Toast.makeText(this,superheroDetalle.titulo, Toast.LENGTH_LONG).show()
        /********************/
        /*progressBar!!.visibility = View.VISIBLE

        i = progressBar!!.progress

        Thread(Runnable {
            // this loop will run until the value of i becomes 99
            while (i < 1000) {
                i += 1
                // Update the progress bar and display the current value
                handler.post(Runnable {
                    progressBar!!.progress = i
                    // setting current progress to the textview
                    //txtView!!.text = i.toString() + "/" + progressBar.max
                })
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }

            // setting the visibility of the progressbar to invisible
            // or you can use View.GONE instead of invisible
            // View.GONE will remove the progressbar
            progressBar!!.visibility = View.INVISIBLE

        }).start()*/
        /********************/

        /*
        val intent : Intent = Intent(this, DetalleActivity::class.java) //::class.java
        intent.putExtra("HEROES_ID", superheroDetalle.id)


        //progressBar!!.visibility = View.GONE
        //si quieres ver la activity esto es necesario
        startActivity(intent)
*/

    }
}