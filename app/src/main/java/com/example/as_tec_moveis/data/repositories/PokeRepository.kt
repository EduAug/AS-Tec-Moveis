package com.example.as_tec_moveis.data.repositories

import android.util.Log
import com.example.as_tec_moveis.commons.Results
import com.example.as_tec_moveis.commons.StringUtils.capitalizeFirst
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
                val pokeid = pokemonResult.url.split("/").dropLast(1).last().toInt()
                val pokemonDetail = pokeAPI.fetchPokemonDetail(pokeid)
                val pokemonSpecies= pokeAPI.fetchPokemonSpecies(pokeid)
                Log.d("PokemonRepository", "Fetched details for Pokémon ID: $pokeid - ${pokemonDetail.name}")
                val flavorTextEntries= pokemonSpecies.flavorTextEntries.filter {
                    it.language.name== "en" && (it.version.name== "sword" || it.version.name== "shield")
                }
                val descriptionSword= flavorTextEntries.find {it.version.name=="sword"}?.flavorText
                val descriptionShield= flavorTextEntries.find {it.version.name=="shield"}?.flavorText
                Log.d("PokemonRepository", "Sword: $descriptionSword")
                Log.d("PokemonRepository", "Shield: $descriptionShield")
                Pokemon(
                    pokeid = pokemonDetail.id,
                    name = pokemonDetail.name.capitalizeFirst(),
                    image = pokemonDetail.sprites.frontDefault,
                    typing = pokemonDetail.types.map { "["+it.type.name.capitalizeFirst()+"]" },
                    descriptionSword = descriptionSword,
                    descriptionShield = descriptionShield
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