package com.example.pokeapi.servicioapi

import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    @SerializedName("results") val listaPokemons: List<Pokemon>
)

class Pokemon (
    val name:String,
    val url:String
){

    fun getId():Int{
        return url.split("/").dropLast(1).last().toInt()
    }
}

class PokemonDetail (
    val name:String,
    val id:Int,
    val weight:Int,
    val height:Int,
)
