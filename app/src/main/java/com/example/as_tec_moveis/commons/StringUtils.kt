package com.example.as_tec_moveis.commons

object StringUtils {

    fun String.capitalizeFirst(): String {
        return this.split(" ").joinToString(" ") {it.replaceFirstChar { char-> char.uppercase() }}
    }
}