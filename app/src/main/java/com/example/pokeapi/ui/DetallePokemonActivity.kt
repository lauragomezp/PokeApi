package com.example.pokeapi.ui

import android.content.res.ColorStateList
import android.graphics.Color
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
import java.util.Locale

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

                            val type = pokemon.types.getOrNull(0)?.type?.name ?: "unknown"
                            Log.d("PokemonDetalle", type)

                            when (type.lowercase(Locale.ROOT)){
                                "grass" ->{
                                    binding.cvPokemon.setCardBackgroundColor(Color.parseColor("#4CAF50"))
                                    binding.tvTitulo.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4CAF50")))
                                    binding.fabVolver.imageTintList = ColorStateList.valueOf(Color.parseColor("#4CAF50"))
                                }
                                "fire" ->{
                                    binding.cvPokemon.setCardBackgroundColor(Color.parseColor("#FF5722"))
                                    binding.tvTitulo.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF5722")))
                                    binding.fabVolver.imageTintList = ColorStateList.valueOf(Color.parseColor("#FF5722"))

                                }
                                "water" ->{
                                    binding.cvPokemon.setCardBackgroundColor(Color.parseColor("#2196F3"))
                                    binding.tvTitulo.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#2196F3")))
                                    binding.fabVolver.imageTintList = ColorStateList.valueOf(Color.parseColor("#2196F3"))

                                }
                                "poison" ->{
                                    binding.cvPokemon.setCardBackgroundColor(Color.parseColor("#7B1FA2"))
                                    binding.tvTitulo.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#7B1FA2")))
                                    binding.fabVolver.imageTintList = ColorStateList.valueOf(Color.parseColor("#7B1FA2"))
                                }
                                "electric" ->{
                                    binding.cvPokemon.setCardBackgroundColor(Color.parseColor("#ffc107"))
                                    binding.tvTitulo.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ffc107")))
                                    binding.fabVolver.imageTintList = ColorStateList.valueOf(Color.parseColor("#ffc107"))
                                }
                                "bug"->{
                                    binding.cvPokemon.setCardBackgroundColor(Color.parseColor("#C2BE4A"))
                                    binding.tvTitulo.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#C2BE4A")))
                                    binding.fabVolver.imageTintList = ColorStateList.valueOf(Color.parseColor("#C2BE4A"))
                                }
                                "fairy"-> {
                                    binding.cvPokemon.setCardBackgroundColor(Color.parseColor("#F8BBD0"))
                                    binding.tvTitulo.setBackgroundTintList(
                                        ColorStateList.valueOf(
                                            Color.parseColor("#F8BBD0")
                                        )
                                    )
                                    binding.fabVolver.imageTintList =
                                        ColorStateList.valueOf(Color.parseColor("#F8BBD0"))
                                }
                                "ground"->{
                                    binding.cvPokemon.setCardBackgroundColor(Color.parseColor("#795548"))
                                    binding.tvTitulo.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#795548")))
                                    binding.fabVolver.imageTintList = ColorStateList.valueOf(Color.parseColor("#795548"))
                                }
                                "dark"->{
                                    binding.cvPokemon.setCardBackgroundColor(Color.parseColor("#212121"))
                                    binding.tvTitulo.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#212121")))
                                    binding.fabVolver.imageTintList = ColorStateList.valueOf(Color.parseColor("#212121"))
                                }
                                "rock"->{
                                    binding.cvPokemon.setCardBackgroundColor(Color.parseColor("#9E9E9E"))
                                    binding.tvTitulo.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#9E9E9E")))
                                    binding.fabVolver.imageTintList = ColorStateList.valueOf(Color.parseColor("#9E9E9E"))

                                }

                                "psychic"->{
                                    binding.cvPokemon.setCardBackgroundColor(Color.parseColor("#7C4DFF"))
                                    binding.tvTitulo.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#7C4DFF")))
                                    binding.fabVolver.imageTintList = ColorStateList.valueOf(Color.parseColor("#7C4DFF"))
                                }


                                else -> {
                                    binding.cvPokemon.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
                                    binding.tvNombre.setTextColor(Color.BLACK)
                                    binding.tvType.setTextColor(Color.BLACK)
                                    binding.tvWeightResult.setTextColor(Color.BLACK)
                                    binding.tvHeightResult.setTextColor(Color.BLACK)
                                    binding.tvExperienceResult.setTextColor(Color.BLACK)
                                    binding.tvExperience.setTextColor(Color.BLACK)
                                    binding.tvHeight.setTextColor(Color.BLACK)
                                    binding.tvWeight.setTextColor(Color.BLACK)
                                }
                            }

                            binding.tvNombre.text = pokemon.name.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
                            binding.tvType.text = type.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
                            binding.tvExperienceResult.text = pokemon.base_experience.toString()
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
