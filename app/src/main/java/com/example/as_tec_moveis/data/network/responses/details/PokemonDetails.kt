package com.example.as_tec_moveis.data.network.responses.details

import com.google.gson.annotations.SerializedName

data class PokemonDetails(
    val id: Int,
    val name: String,
    @SerializedName("sprites")
    val sprites: Sprites,
    val types: List<PokemonType>
)