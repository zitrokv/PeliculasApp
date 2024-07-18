package com.example.peliculasapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.MainThread
import androidx.recyclerview.widget.RecyclerView
import com.example.peliculasapp.data.PeliculasResponse
import com.example.peliculasapp.databinding.ItemPeliculasBinding
import com.squareup.picasso.Picasso

class PeliculasAdapter(
    private var dataSet: List<PeliculasResponse>, // PeliculasListResponse,
    private val onItemClickListener: (Int) -> Unit
                        ): RecyclerView.Adapter<PeliculasViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeliculasViewHolder {
        val binding = ItemPeliculasBinding.inflate(LayoutInflater.from(parent.context))
        //return PeliculasViewHolder(ItemPeliculasBinding.bind(parent.rootView))
        return PeliculasViewHolder(binding)
    }

    override fun getItemCount(): Int = dataSet.size

    override fun onBindViewHolder(holder: PeliculasViewHolder, position: Int) {
        holder.render(dataSet[position])
        holder.itemView.setOnClickListener{
            onItemClickListener(position)
            //onItemClickListener(holder.adapterPosition) // es la posicion más real, se calcula dinamicamente!!
        }
    }
    fun updateData(dataSet: List<PeliculasResponse>, highlight: String? ){
        //this.highlightText = highlight
        this.dataSet = dataSet
        notifyDataSetChanged()
    }

}

class PeliculasViewHolder(val binding: ItemPeliculasBinding) : RecyclerView.ViewHolder(binding.root){

    fun render(pelicula :PeliculasResponse){

        binding.nameTextView.text = pelicula.titulo
        Picasso.get().load(pelicula.poster).into(binding.avatarImageView)
    }

}