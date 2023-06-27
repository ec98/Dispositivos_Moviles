package com.example.dispositivosmoviles.ui.data.endPoints

import com.example.dispositivosmoviles.ui.data.entities.jikan.JikanAnimeEntity
import retrofit2.Response
import retrofit2.http.GET

interface JikanEndpoint {

    @GET("top/anime")
    suspend fun getAllAnimes(): Response<JikanAnimeEntity>

    //Aqui se deben escribir todos los llamados que se necesiten
    //Pero se debera crear el dataclass necesario para que funcione dicho llamado

}