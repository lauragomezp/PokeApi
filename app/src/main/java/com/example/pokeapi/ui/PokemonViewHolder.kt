package com.example.pokeapi.ui

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapi.R
import com.example.pokeapi.databinding.ItemPokemonBinding
import com.example.pokeapi.servicioapi.Pokemon
import com.squareup.picasso.Picasso

class PokemonViewHolder (view: View): RecyclerView.ViewHolder(view){
    private val binding = ItemPokemonBinding.bind(view)

    fun bind(pokemon: Pokemon){
        binding.tvNombrePokemon.text = pokemon.name.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
        Picasso.get().load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemon.getId()}.png").into(binding.ivPokemon)
    }

}