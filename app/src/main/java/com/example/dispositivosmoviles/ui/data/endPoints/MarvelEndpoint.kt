package com.example.dispositivosmoviles.ui.data.endPoints

import com.example.dispositivosmoviles.ui.data.entities.marvel.marvelData.MarvelApiCharacters
import com.example.dispositivosmoviles.ui.logic.data.marvelCharacters
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelEndpoint {

    @GET("characters")
    fun getCharactersStartWith(
        @Query("nameStartsWith") name: String,
        @Query("limit") limit: Int,
        //valores por omision (referenciado a la pagina de enlace
        //los siguientes parametros)
        @Query("ts") ts: String = "uce1",
        @Query("apikey") apikey: String = "48ed26ff242038147ce24450236a7ec2",
        @Query("hash") hash: String = "e39fb11ad271b98d8cac028063ce639b"

    ): Response<MarvelApiCharacters>

    @GET("characters")
    suspend fun getAllMarvelCharacters(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
        @Query("ts") ts: String = "uce1",
        @Query("apikey") apikey: String = "48ed26ff242038147ce24450236a7ec2",
        @Query("hash") hash: String = "e39fb11ad271b98d8cac028063ce639b"

    ): Response<MarvelApiCharacters>
}