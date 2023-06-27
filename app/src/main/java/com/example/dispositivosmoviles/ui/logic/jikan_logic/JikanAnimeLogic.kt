package com.example.dispositivosmoviles.ui.logic.jikan_logic

import com.example.dispositivosmoviles.ui.data.connections.ApiConnection
import com.example.dispositivosmoviles.ui.data.endPoints.JikanEndpoint
import com.example.dispositivosmoviles.ui.data.marvel.marvelCharacters

class JikanAnimeLogic {

    suspend fun getAllanimes(): List<marvelCharacters> {

        var itemsList = arrayListOf<marvelCharacters>()

        val response = ApiConnection.getService(
            ApiConnection.typeApi.Jikan,
            JikanEndpoint::class.java
        ).getAllAnimes()


        if (response.isSuccessful) {
            response.body()!!.data.forEach {

                val m = marvelCharacters(
                    it.mal_id,
                    it.title,
                    it.images.jpg.image_url,
                    it.titles[0].title
                )
                itemsList.add(m)
            }
        }
        return itemsList

//    suspend fun getAllAnimes(): List<marvelCharacters> {
//        var call = ApiConnection.getJikanConnection()
//        val response = call.create(JikanEndpoint::class.java).getAllAnimes()
//
//        var itemList = arrayListOf<marvelCharacters>()
//
//        if (response.isSuccessful) {
//            response.body()!!.data.forEach {
//                val m = marvelCharacters(
//                    it.mal_id,
//                    it.title,
//                    it.titles[0].title,
//                    it.images.jpg.image_url
//                )
//                itemList.add(m)
//            }
//        }
//        return itemList
//    }
    }
}