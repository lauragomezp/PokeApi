package com.example.pokeapi.servicioapi

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {
    @GET("pokemon") // Replace with your actual endpoint
    suspend fun getPokemonList(@Query("limit") limit: Int): Response<PokemonResponse>

    @GET("pokemon/{id}") // Use a path parameter for dynamic IDs
    suspend fun getPokemonDetails(@Path("id") id: Int): Response<PokemonDetail>
}