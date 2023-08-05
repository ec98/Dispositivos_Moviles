package com.example.dispositivosmoviles.ui.ui.viewmodels

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dispositivosmoviles.ui.logic.data.marvelCharacters
import com.example.dispositivosmoviles.ui.logic.marvel_logic.MarvelLogic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ProgressViewModel: ViewModel() {

    val progressState = MutableLiveData<Int>()
    val items = MutableLiveData<List<marvelCharacters>>()

    //corrutina, mientras el vm este vivo. Se asocia al lifecycleScope
    fun processBackground(valor: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            val state = View.VISIBLE
            progressState.postValue(state) //toma el estado

            delay(valor)

            val state1 = View.GONE
            progressState.postValue(state1)
        }
    }

    fun sumaBackground() {
        viewModelScope.launch(Dispatchers.IO) {
            val state = View.VISIBLE
            progressState.postValue(state) //toma el estado

            var total: Long = 0
            for (i in 1..6000) {
                total += i
            }
            delay(total)

            val state1 = View.GONE
            progressState.postValue(state1)
        }
    }

    suspend fun getMarvelChars(offset: Int, limit: Int) {
        progressState.postValue(View.VISIBLE)
        val newItems = MarvelLogic().getAllMarvelCharacters(offset, limit)
        items.postValue(newItems)
        progressState.postValue(View.GONE)
    }

}