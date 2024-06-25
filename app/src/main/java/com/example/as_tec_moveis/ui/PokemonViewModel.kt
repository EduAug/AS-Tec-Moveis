package com.example.as_tec_moveis.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.as_tec_moveis.commons.Results
import com.example.as_tec_moveis.data.Pokemon
import com.example.as_tec_moveis.data.repositories.PokeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val repository: PokeRepository
): ViewModel(){
    private val _pokeList= MutableLiveData<Results<List<Pokemon>>>()
    val pokemonList: LiveData<Results<List<Pokemon>>> get()= _pokeList

    fun fetchPokemonList(offset: Int, limit: Int){
        _pokeList.value= Results.Loading
        viewModelScope.launch {
            val result= repository.getPokemonList(offset, limit)
            _pokeList.postValue(result)
        }
    }
}