package com.example.peliculasapp.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.drawToBitmap
import com.example.peliculasapp.R
import com.example.peliculasapp.data.PeliculasApiService
import com.example.peliculasapp.data.PeliculasListResponse
import com.example.peliculasapp.databinding.ActivityDetalleBinding
import com.example.peliculasapp.utils.RetrofitUtils
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class DetalleActivity : AppCompatActivity() {
    lateinit var bindingDetalle : ActivityDetalleBinding
    //lateinit var sesion: SessionManager

    lateinit var detalleActivity: PeliculasListResponse
    lateinit var textoDelDia : TextView
    lateinit var MenuItemFavorito : MenuItem

    var esFavorito = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingDetalle = ActivityDetalleBinding.inflate(layoutInflater)
        setContentView(bindingDetalle.root)

        //sesion = SessionManager(this)

        val id = intent.getStringExtra("FILM_ID")

        //Log.i("INFO",id.toString())

        //Toast.makeText(this, id.toString(), Toast.LENGTH_LONG).show()
        //val id = intent.getIntExtra("HOROSCOPO_ID",-1)
        //detalleActivity = SuperheroProvider.findById(id)

        bindingDetalle.matchParent.setBackgroundColor(Color.DKGRAY)
        bindingDetalle.DetalleTextView.text = ""//(id).toString()
        searchById(id!!)
        //bindingDetalle.DetalleTextView.text = getString(id)


        //findViewById<TextView>(R.id.DetalleTextView).setText(id)
        /*findViewById<TextView>(R.id.DetalleTextView).setText(detalleHoroscopo.nombre)
        findViewById<ImageView>(R.id.detalleImageView).setImageResource(detalleHoroscopo.logo)
        findViewById<ImageView>(R.id.waterImageView).setImageResource(detalleHoroscopo.logo)
        findViewById<ImageView>(R.id.waterImageView).imageAlpha = 28
        findViewById<ImageView>(R.id.waterImageView).setBackgroundColor(255)
        findViewById<TextView>(R.id.prediccionTextView).setText(
            LocalDate.now().format(
            DateTimeFormatter.ofPattern("dd-MM-yyyy")).toString())*/

        //icono en menu detalle
        //esFavorito = sesion.obtenerHoroscopoFavorito()?.equals(detalleHoroscopo.id) ?: false

        //textoDelDia = findViewById(R.id.textoDelDia)
        //supportActionBar?.setTitle(detalleHoroscopo.nombre)
        //supportActionBar?.setSubtitle(detalleHoroscopo.fecha)
        //supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //getDailyHoroscope()

        /*ImagenFavorito.setOnClickListener{
            if (esFavorito)
            {
                sesion.enviarHoroscopoFavorito("")
            }
            else{
                sesion.enviarHoroscopoFavorito((detalleHoroscopo.id))
            }

            esFavorito = !esFavorito

        }*/

        //findViewById<TextView>(R.id.DetalleTextView).text = getString(detalleHoroscopo.nombre)
        //findViewById<ImageView>(R.id.detalleImageView).setImageResource(detalleHoroscopo.logo)



    }

    private fun searchById(query: String){
        //segundo plano ó hilo secundario paralelo
        CoroutineScope(Dispatchers.IO).launch {
            try {
                //val apiService2 = getRetrofit().create(SuperheroApiService::class.java)
                val apiService = RetrofitUtils.getRetrofit().create(PeliculasApiService::class.java)
                val result = apiService.getById(query)


                runOnUiThread {
                    //Toast.makeText(this, result.id, Toast.LENGTH_LONG).show()
                    //bindingDetalle.detalleImageView.setImageDrawable()
                    Picasso.get().load(result.poster).into(bindingDetalle.detalleImageView)
                    bindingDetalle.waterImageView.setImageBitmap(bindingDetalle.detalleImageView.drawToBitmap())
                    bindingDetalle.DetalleTextView.text = result.titulo
                    bindingDetalle.prediccionTextView.text = result.director
                    bindingDetalle.textoDelDia.text = result.sinopsis

                    //Toast.makeText(this@DetalleActivity, result.labelJSON.resultado.campoNombre.toString(), Toast.LENGTH_LONG*2).show()
                    //bindingDetalle.textoDelDia.text = result.labelJSON.r.toString()
                    //bindingDetalle.textoDelDia.text = result.labelJSON.resultado.campoNombre

                    //bindingDetalle.waterImageView.drawable



                    Picasso.get().load(result.poster).into(bindingDetalle.waterImageView)
                    findViewById<ImageView>(R.id.waterImageView).imageAlpha = 28
                    findViewById<ImageView>(R.id.waterImageView).setBackgroundColor(255)
                    //Picasso.get().load(result.image.url).into(bindingDetalle.waterImageView)


                    /*if (result.response == "success") {
                        superheroList = result.results
                        adapter.updateData(superheroList, query)
                    } else {
                        adapter.updateData(emptyList(), query)
                    }*/
                }

            }catch (e: Exception){
                e.printStackTrace()
            }
        }

    }
}