package com.example.dispositivosmoviles.ui.data.endPoints

import com.example.dispositivosmoviles.ui.data.entities.marvel.marvelData.MarvelApiCharacters
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelEndpoint {

    @GET("characters")
    fun getCharactersStartWith(
        @Query("nameStartsWith") name: String,
        @Query("limit") limit: Int,
        //valores por omision
        @Query("ts") ts: String = "uce",
        @Query("apikey") apikey: String = "48ed26ff242038147ce24450236a7ec2",
        @Query("hash") hash: String = "f00af94ad24dd1d56b2ea26ae903030e"

    ): Response<MarvelApiCharacters>
}