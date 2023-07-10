package com.example.dispositivosmoviles.ui.logic.marvel_logic

import android.util.Log
import com.example.dispositivosmoviles.ui.data.connections.ApiConnection
import com.example.dispositivosmoviles.ui.data.endPoints.MarvelEndpoint
import com.example.dispositivosmoviles.ui.data.entities.marvel.marvelData.getMarvelChars
import com.example.dispositivosmoviles.ui.logic.data.marvelCharacters

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
                response.body()!!.data.results.forEach {
                    val m = it.getMarvelChars()
                    itemsList.add(m)
//                response.body()!!.data.results.forEach {
//                    var commic: String = "No Available"
//                    if (it.comics.items.size > 0) {
//                        commic = it.comics.items[0].name
//                    }
//                    val m = marvelCharacters(
//                        it.id,
//                        it.name,
//                        commic,
//                        it.thumbnail.path + "." + it.thumbnail.extension
//                    )
//                    itemsList.add(m)
//                }
                }
            } else {
                Log.d("UCE", response.toString())
            }
        }
        return itemsList
    }

    suspend fun getAllMarvelCharacters(offset: Int, limit: Int): ArrayList<marvelCharacters> {
        var call =
            ApiConnection.getService(ApiConnection.typeApi.Marvel, MarvelEndpoint::class.java)
        //val response = call.create(JikanEndpoint::class.java).getAllAnimes()
        val itemList = arrayListOf<marvelCharacters>()

        if (call != null) {
            val response = call.getAllMarvelChar(offset, limit)

            if (response.isSuccessful) {
                response.body()!!.data.results.forEach() {
                    val m = it.getMarvelChars()
                    itemList.add(m)
                }
            } else {
                Log.d("UCE", response.toString())
            }

        }
        return itemList


    }
}