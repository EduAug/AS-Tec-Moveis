package com.example.as_tec_moveis.commons

import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.configureToolbar(title: String, enableBackButton: Boolean){
    supportActionBar?.apply {
        this.title= title
        setDisplayHomeAsUpEnabled(enableBackButton)
    }
}