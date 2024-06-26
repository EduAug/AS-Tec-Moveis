package com.example.as_tec_moveis.data.network

import com.example.as_tec_moveis.data.network.responses.PokeResponse
import com.example.as_tec_moveis.data.network.responses.details.PokemonDetails
import com.example.as_tec_moveis.data.network.responses.details.PokemonSpeciesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeAPI {
    @GET("pokemon")
    suspend fun fetchPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): PokeResponse

    @GET("pokemon/{id}")
    suspend fun fetchPokemonDetail(@Path("id") id: Int): PokemonDetails

    @GET("pokemon-species/{id}")
    suspend fun fetchPokemonSpecies(@Path("id") id: Int): PokemonSpeciesResponse
}