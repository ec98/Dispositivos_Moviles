package com.example.dispositivosmoviles.ui.data.dao.marvel

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.dispositivosmoviles.ui.data.entities.marvel.marvelData.database.MarvelPersonajesDB

//colocar la anotacion @dao, antes debes implementar las dependencias
//Dato importante: antes de realizar el dao y los querys, necesitas crear la base de datos de tal DATO
@Dao
interface MarvelPersonajesDAO {

//    antes de colocar las funciones,necesitas la base de datos de DATO
//    en este caso MarvelPersonajesDB

    //    ahora si
    @Query("select * from MarvelPersonajesDB")
    fun getAllCharacters(): List<MarvelPersonajesDB>

    @Query("select * from MarvelPersonajesDB where id =:pk")
    fun getOneCharacter(pk: Int): MarvelPersonajesDB

    @Insert
    fun insertMarvelChar(ch: List<MarvelPersonajesDB>)
}