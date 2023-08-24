package com.example.dispositivosmoviles.ui.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dispositivosmoviles.ui.logic.data.marvelCharacters
import com.example.dispositivosmoviles.ui.logic.marvel_logic.MarvelLogic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FirstViewModel : ViewModel() {

    private val limit = 9
    private var offset = 0

    private val marvelCharactersModelLiveData = MutableLiveData<List<marvelCharacters>>()
    val marvelCharactersLiveData : LiveData<List<marvelCharacters>> = marvelCharactersModelLiveData

    fun chargeDataRVInit() {
        viewModelScope.launch(Dispatchers.IO) {
            val marvelCharacters = withContext(Dispatchers.IO){
                MarvelLogic().getInitChar(offset, limit)
            }
            withContext(Dispatchers.Main){
                marvelCharactersModelLiveData.value = marvelCharacters.toMutableList()
                offset += limit
            }
        }
    }

}