package com.example.dispositivosmoviles.ui.logic.marvel_logic

import android.util.Log
import com.example.dispositivosmoviles.ui.data.connections.ApiConnection
import com.example.dispositivosmoviles.ui.data.endPoints.MarvelEndpoint
import com.example.dispositivosmoviles.ui.data.entities.marvel.marvelData.database.MarvelPersonajesDB
import com.example.dispositivosmoviles.ui.data.entities.marvel.marvelData.database.getMarvelChars
import com.example.dispositivosmoviles.ui.data.entities.marvel.marvelData.getMarvelChars
import com.example.dispositivosmoviles.ui.logic.data.getMarvelCharsDB
import com.example.dispositivosmoviles.ui.logic.data.marvelCharacters
import com.example.dispositivosmoviles.ui.ui.utilities.DispositivosMoviles
import java.lang.RuntimeException

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
                }
            } else {
                Log.d("UCE", response.toString())
            }
        }
        return itemsList
    }

    suspend fun getAllMarvelCharacters(offset: Int, limit: Int): ArrayList<marvelCharacters> {
        val itemList = arrayListOf<marvelCharacters>()
        var call =
            ApiConnection.getService(
                ApiConnection.typeApi.Marvel,
                MarvelEndpoint::class.java
            )

        if (call != null) {
            val response = call.getAllMarvelCharacters(offset, limit)
            Log.d("UCE", response.toString())
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

    suspend fun getAllMarvelCharsDB(): MutableList<marvelCharacters> {
        var items: ArrayList<marvelCharacters> = arrayListOf()
        DispositivosMoviles.getDbInstance().marvelDao().getAllCharacters().forEach {
            items.add(
                it.getMarvelChars()
            )
        }
        return items
    }

    suspend fun getInitChar(offset: Int, limit: Int): MutableList<marvelCharacters> {
        var items = mutableListOf<marvelCharacters>()
        try {
            items = MarvelLogic()
                .getAllMarvelCharsDB()
                .toMutableList()
            if (items.isEmpty()) {
                items = (MarvelLogic()
                    .getAllMarvelCharacters(
                        offset,
                        limit
                    ))
            }
        } catch (ex: Exception) {
            throw RuntimeException(ex.message)
        } finally {
            return items
        }
    }

    suspend fun insertMarvelCharstoDB(items: List<marvelCharacters>) {
        var itemsDB = arrayListOf<MarvelPersonajesDB>()
        items.forEach {
            itemsDB.add(
                it.getMarvelCharsDB()
            )
        }
        DispositivosMoviles.getDbInstance().marvelDao().insertMarvelChar(
            itemsDB
        )
    }
}