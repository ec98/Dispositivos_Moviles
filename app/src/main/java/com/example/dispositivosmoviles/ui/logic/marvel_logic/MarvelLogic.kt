package com.example.dispositivosmoviles.ui.logic.marvel_logic

import android.util.Log
import com.example.dispositivosmoviles.ui.data.connections.ApiConnection
import com.example.dispositivosmoviles.ui.data.endPoints.MarvelEndpoint
import com.example.dispositivosmoviles.ui.data.marvel.marvelCharacters

class MarvelLogic {

    suspend fun getMarvelChars(name: String, limit: Int): ArrayList<marvelCharacters> {

        var itemsList = arrayListOf<marvelCharacters>()

        val call = ApiConnection.getService(
            ApiConnection.typeApi.Marvel,
            MarvelEndpoint::class.java
        )

        if (call != null) {
            val response = call.getCharactersStartWith(name, limit)
            if (response.isSuccessful) {
                response.body()!!.data.results.forEach() {
                    var commic: String = "No Available"
                    if (it.comics.items.size > 0) {
                        commic = it.comics.items[0].name
                    }
                    val m = marvelCharacters(
                        it.id,
                        it.name,
                        commic,
                        it.thumbnail.path + "." + it.thumbnail.extension
                    )
                    itemsList.add(m)
                }
            } else {
                Log.d("UCE", response.toString())
            }
        }
        return itemsList
    }
}