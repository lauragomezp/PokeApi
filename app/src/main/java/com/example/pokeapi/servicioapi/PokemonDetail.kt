package com.example.pokeapi.servicioapi

data class PokemonDetail(
    val name:String = "",
    val id:Int = 0,
    val weight:Int = 0,
    val height:Int = 0,
    val base_experience:Int = 0,
    val types:List<Type> = emptyList()
)

class Type{
    val slot:Int = 0
    val type:TypeDetail = TypeDetail()
}

class TypeDetail {
    val name:String = ""
    val url:String = ""
}

