package com.example.pokeapi.servicioapi

import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    //https://pokeapi.co/api/v2/pokemon/
    @GET("pokemon")
    suspend fun getPokemonList(@Query("limit") limit: Int): PokemonResponse

}