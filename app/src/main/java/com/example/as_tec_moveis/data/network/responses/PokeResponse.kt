package com.example.as_tec_moveis.data.network.responses

import com.google.gson.annotations.SerializedName

data class PokeResponse (
    @SerializedName("results")
val pokeResult:List<PokeResults>
)