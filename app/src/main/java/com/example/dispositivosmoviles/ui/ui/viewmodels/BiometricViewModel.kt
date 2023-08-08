package com.example.dispositivosmoviles.ui.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay

class BiometricViewModel : ViewModel() {

    var isLoading = MutableLiveData<Boolean>()

    //simular la carga
    suspend fun chargerData(){
        isLoading.postValue(true)
        delay(5000)
        isLoading.postValue(false)
    }
}