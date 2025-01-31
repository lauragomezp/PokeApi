package com.example.pokeapi.servicioapi

import com.google.gson.annotations.SerializedName

class PokemonResponse (
    val status:String,
    @SerializedName("result") val listaPokemons: List<Pokemon>
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
