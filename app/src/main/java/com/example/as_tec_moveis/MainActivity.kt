package com.example.as_tec_moveis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.as_tec_moveis.commons.Results
import com.example.as_tec_moveis.databinding.ActivityMainBinding
import com.example.as_tec_moveis.ui.PokemonViewModel
import com.example.as_tec_moveis.ui.adapters.PokemonAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: PokemonViewModel by viewModels()
    private lateinit var adapter: PokemonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configureRecycler()
        observer()
    }

    private fun configureRecycler(){
        adapter= PokemonAdapter()
        binding.recyclerView.adapter= adapter
    }

    private fun observer(){
        viewModel.pokemonList.observe(this) {result->
            when(result){
                is Results.Loading-> {
                    binding.progBar.visibility= View.VISIBLE
                }
                is Results.Success -> {
                    binding.progBar.visibility = View.GONE
                    adapter.setUpPokemon(result.data)
                }
                is Results.Error -> {
                    binding.progBar.visibility = View.GONE
                }

                else -> {
                    Log.d("MainActivity","Woops, done with observer, hit else")}
            }
        }
        viewModel.fetchPokemonList(offset = 809, limit = 89)
    }
}