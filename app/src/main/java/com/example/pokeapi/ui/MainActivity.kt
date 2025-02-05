package com.example.pokeapi.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokeapi.R
import com.example.pokeapi.databinding.ActivityMainBinding
import com.example.pokeapi.servicioapi.APIService
import com.example.pokeapi.servicioapi.Pokemon
import com.example.pokeapi.servicioapi.PokemonResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    lateinit var pokemonAdapter: PokemonAdapter
    lateinit var context: Context
    var pokemonList: List<Pokemon>? = null
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this

        mostrarPokemons()
    }

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://pokeapi.co/api/v2/").addConverterFactory(
            GsonConverterFactory.create()).build()
    }

    fun mostrarPokemons(){
       CoroutineScope(Dispatchers.IO).launch {
           val response = getRetrofit().create(APIService::class.java).getPokemonList(50)
           Log.d("Prueba", response.code().toString())
           if(response.isSuccessful){
               val pokemonResponse = response.body() as PokemonResponse
               withContext(Dispatchers.Main) {
                   pokemonList = pokemonResponse.listaPokemons
                   if (pokemonList != null){
                       if (pokemonList!!.isNotEmpty()){
                           pokemonAdapter = PokemonAdapter(pokemonList!!)
                           binding.rvPokemons.layoutManager = LinearLayoutManager(context)
                           binding.rvPokemons.adapter = pokemonAdapter
                       }else {
                           Toast.makeText(context, "La lista esta vacia", Toast.LENGTH_SHORT).show()
                       }
                   }else{
                       Toast.makeText(context, "La lista es nula", Toast.LENGTH_SHORT).show()
                   }
               }
           } else {
               withContext(Dispatchers.Main) {
                   Toast.makeText(
                       context,
                       "No se ha ejecutado correctamente la query. Error: "+response.code(),
                       Toast.LENGTH_LONG
                   ).show()

               }
           }


       }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

}