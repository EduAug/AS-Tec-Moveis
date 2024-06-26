package com.example.as_tec_moveis.data.network.responses.details

import com.google.gson.annotations.SerializedName

data class FlavorTextEntry(
    @SerializedName("flavor_text") val flavorText: String,
    @SerializedName("language") val language: Language,
    @SerializedName("version") val version: Version
)
