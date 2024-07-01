package com.example.as_tec_moveis.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.as_tec_moveis.R
import com.example.as_tec_moveis.commons.Results
import com.example.as_tec_moveis.data.Pokemon
import com.example.as_tec_moveis.databinding.FragmentHomeBinding
import com.example.as_tec_moveis.ui.PokemonViewModel
import com.example.as_tec_moveis.ui.adapters.PokemonAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: PokemonAdapter
    private lateinit var pokemonViewModel: PokemonViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pokemonViewModel= ViewModelProvider(this)[PokemonViewModel::class.java]

        createAdapter()
        observer()
        pokemonViewModel.fetchPokemonList(offset = 809, limit = 89)
    }

    private fun createAdapter(){
        adapter= PokemonAdapter(goToDetail= ::goToDetail)
        binding.rcListPokemon.adapter= adapter
    }

    private fun observer(){
        pokemonViewModel.pokemonList.observe(viewLifecycleOwner) {result->
            when(result){
                is Results.Loading-> {
                    binding.progressBar.visibility= View.VISIBLE
                    binding.rcListPokemon.visibility= View.GONE
                }
                is Results.Success -> {
                    binding.progressBar.visibility= View.GONE
                    binding.rcListPokemon.visibility= View.VISIBLE
                    adapter.setUpPokemon(result.data)
                }
                is Results.Error -> {
                    binding.progressBar.visibility= View.GONE
                    binding.rcListPokemon.visibility= View.GONE
                    Log.i("HomeFragmentError", "Error in observer")
                }

                else -> {
                    Log.d("MainActivity","Whoops, done with observer, hit else")}
            }
        }
    }

    private fun goToDetail(pokemon: Pokemon){
        val bundle= bundleOf("data" to pokemon)
        findNavController().navigate(R.id.action_homeFragment_to_detailsActivity, bundle)
    }
}