package com.example.as_tec_moveis.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.as_tec_moveis.data.Pokemon
import com.example.as_tec_moveis.databinding.PokeItemBinding

class PokemonAdapter(
    private val goToDetail: (pokemon: Pokemon) -> Unit
): RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {
    private lateinit var context: Context
    private lateinit var binding: PokeItemBinding
    private var listPokemon: List<Pokemon> = emptyList()

    fun setUpPokemon(pokemon: List<Pokemon>){
        listPokemon= pokemon
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context= parent.context
        binding= PokeItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int= listPokemon.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listPokemon[position])
        binding.executePendingBindings()
    }

    inner class ViewHolder(private val bindingHolder: PokeItemBinding) : RecyclerView.ViewHolder(bindingHolder.root) {
        fun bind(pokemon: Pokemon) {
            bindingHolder.chara = pokemon

            bindingHolder.root.setOnClickListener {
                goToDetail(pokemon)
            }
        }
    }
}