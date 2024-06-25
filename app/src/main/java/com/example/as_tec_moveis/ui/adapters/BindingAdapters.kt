package com.example.as_tec_moveis.ui.adapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingAdapters {
    @BindingAdapter("loadImage")
    @JvmStatic
    fun loadImage(view: ImageView, url: String){
        Glide.with(view.context)
            .load(url)
            .centerCrop()
            .into(view)
    }

    @BindingAdapter("pokemonTypes")
    @JvmStatic
    fun setPokemonTypes(view: TextView, types: List<String>){
        types.let{
            view.text= it.joinToString (", ")
        }
    }
}