package com.example.as_tec_moveis.data

data class Pokemon(
    val pokeid: Int,
    val name: String,
    val image: String,
    val typing: List<String>,
    val descriptionSword: String?,
    val descriptionShield: String?
)
