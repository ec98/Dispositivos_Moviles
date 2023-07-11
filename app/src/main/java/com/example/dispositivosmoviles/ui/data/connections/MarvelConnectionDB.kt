package com.example.dispositivosmoviles.ui.data.connections

//import androidx.room.Database
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dispositivosmoviles.ui.data.dao.marvel.MarvelPersonajesDAO
import com.example.dispositivosmoviles.ui.data.entities.marvel.marvelData.database.MarvelPersonajesDB

//base de datos pequeña
@Database(
    entities = [MarvelPersonajesDB::class],
    version = 1
)
abstract class MarvelConnectionDB : RoomDatabase() {

    //creamos un DAO de tipo marvel y que sea abstracto
    //el tipo debe ser DAO del dato creado
    abstract fun marvelDao(): MarvelPersonajesDAO
}