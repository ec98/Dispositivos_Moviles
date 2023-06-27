package com.example.dispositivosmoviles.ui.data.connections

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConnection {

//    fun getJikanConnection(): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl("https://api.jikan.moe/v4/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }

    enum class typeApi {
        Jikan, Marvel
    }

    private val API_JIKAN = "https://api.jikan.moe/v4/"
    private val API_MARVEL = "https://gateway.marvel.com/v1/public/"
//    private val API_PETS = "https://gateway.marvel.com/v1/public/"


    private fun getConnection(base: String): Retrofit {
        var retrofit = Retrofit.Builder()
            .baseUrl(base)
            .addConverterFactory(GsonConverterFactory.create())// para utilizar Gson ConverterFactory toca importar a mano la linea 4
            .build()
        return retrofit
    }

    suspend fun <T, E : Enum<E>> getService(api: E, service: Class<T>): T {
        var BASE = ""
        when (api.name) {
            typeApi.Jikan.name -> {
                BASE = API_JIKAN
            }

            typeApi.Marvel.name -> {
                BASE = API_MARVEL
            }
//
//            typeApi.Pets.name -> {
//                BASE = API_PETS
//            }
        }
        return getConnection(BASE).create(service)
    }
}