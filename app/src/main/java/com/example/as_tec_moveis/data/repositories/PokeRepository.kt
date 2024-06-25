package com.example.as_tec_moveis.data.repositories

import android.util.Log
import com.example.as_tec_moveis.commons.Results
import com.example.as_tec_moveis.data.Pokemon
import com.example.as_tec_moveis.data.network.PokeAPI
import javax.inject.Inject

class PokeRepository @Inject constructor(
    private val pokeAPI: PokeAPI
) {
    suspend fun getPokemonList(offset: Int, limit: Int): Results<List<Pokemon>> {
        return try {
            val pokeResponse = pokeAPI.fetchPokemonList(offset, limit)
            val pokemonList = pokeResponse.pokeResult.map { pokemonResult ->
                val segments = pokemonResult.url.split("/")
                val pokeId = segments[segments.size - 2].toInt()
                val pokemonDetail = pokeAPI.fetchPokemonDetail(pokeId)
                Log.d("PokemonRepository", "Fetched details for Pokémon ID: $pokeId - ${pokemonDetail.name}")

                Pokemon(
                    pokeid = pokemonDetail.id,
                    name = pokemonDetail.name,
                    image = pokemonDetail.sprites.frontDefault,
                    typing = pokemonDetail.types.map { it.type.name }
                )
            }

            Log.d("PokemonRepository", "Fetched ${pokemonList.size} Pokémon")
            Results.Success(pokemonList)
        }catch (e: Exception){
            Log.e("PokemonRepository", "Error fetching in repository", e)
            Results.Error(e)
        }
    }
}