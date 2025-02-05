package com.example.pokeapi.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokeapi.R
import com.example.pokeapi.databinding.ActivityMainBinding
import com.example.pokeapi.databinding.DetallePokemonBinding
import com.example.pokeapi.servicioapi.APIService
import com.example.pokeapi.servicioapi.PokemonDetail
import com.example.pokeapi.servicioapi.PokemonResponse
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetallePokemonActivity : AppCompatActivity() {

        private lateinit var binding: DetallePokemonBinding
        private lateinit var pokemon: PokemonDetail

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = DetallePokemonBinding.inflate(layoutInflater)
            setContentView(binding.root)

            val requestIntent = intent.extras
            val pokemonId = requestIntent?.getInt("id")

            Log.d("PokemonDetalle", pokemonId.toString())

            if (pokemonId != null) {
                pokemonDetalle(pokemonId)
            }

            binding.fabVolver.setOnClickListener{
                finish()
            }


        }

        fun getRetrofit(): Retrofit {
            return Retrofit.Builder().baseUrl("https://pokeapi.co/api/v2/").addConverterFactory(
                GsonConverterFactory.create()).build()
        }

        private fun pokemonDetalle(id: Int) {

            Log.d("PokemonDetalle", id.toString())

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val response = getRetrofit().create(APIService::class.java).getPokemonDetails(id)
                    Log.d("PokemonDetalle", response.code().toString())

                    if (response.isSuccessful) {
                        pokemon = response.body()!!

                        withContext(Dispatchers.Main) {

                            binding.tvNombre.text = pokemon.name.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
                            binding.tvWeightResult.text = pokemon.weight.toString()
                            binding.tvHeightResult.text = pokemon.height.toString()


                            val imagenURL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${id}.png"
                            if (imagenURL != null) {
                                Picasso.get().load(imagenURL).into(binding.ivPokemonUrl)
                            }

                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            Log.e("DetallePokemonActivity", "Error: ${response.code()}")
                            // Handle error, e.g., display an error message
                        }
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Log.e("DetallePokemonActivity", "Exception: ${e.message}")
                        // Handle exception
                    }
                }
            }
        }
    }
