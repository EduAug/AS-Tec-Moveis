package com.example.as_tec_moveis.commons

sealed class Results<out T> {
    data object Loading: Results<Nothing>()
    data class Success<out T>(val data: T): Results<T>()
    data class Error(val exception: Exception): Results<Nothing>()
}