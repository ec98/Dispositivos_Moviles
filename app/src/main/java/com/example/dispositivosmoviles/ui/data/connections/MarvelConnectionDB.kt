package com.example.dispositivosmoviles.ui.data.connections

//import androidx.room.Database
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dispositivosmoviles.ui.data.dao.marvel.MarvelPersonajesDAO

//base de datos pequeña
@Database(
    entities = [MarvelPersonajesDAO::class],
    version = 1
)
abstract class MarvelConnectionDB : RoomDatabase() {

    //creamos un DAO de tipo marvel y que sea abstracto
    //el tipo debe ser DAO del dato creado
    abstract fun marvelDao(): MarvelPersonajesDAO
}