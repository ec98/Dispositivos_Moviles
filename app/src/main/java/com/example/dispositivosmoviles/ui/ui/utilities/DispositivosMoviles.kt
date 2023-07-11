package com.example.dispositivosmoviles.ui.ui.utilities

import android.app.Application
import androidx.room.Room
import com.example.dispositivosmoviles.ui.data.connections.MarvelConnectionDB

//de aplicacion
class DispositivosMoviles : Application() {
    //esta clase es atada al ciclo de vida
    //solo debe ser uno
    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            applicationContext,
            MarvelConnectionDB::class.java,
            "marvelDB"
        ).build()
    }

    //un companion object es un objeto que sea crea
    //dentro de una clase
    companion object {
        private var db: MarvelConnectionDB? = null
        fun getDbInstance(): MarvelConnectionDB {
            return db!! //no esta vacia
        }
    }

}