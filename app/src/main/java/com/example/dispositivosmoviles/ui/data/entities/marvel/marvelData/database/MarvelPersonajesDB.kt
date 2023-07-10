package com.example.dispositivosmoviles.ui.data.entities.marvel.marvelData.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
//import androidx.room.Entity
//import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
//especificar que es una identidad
@Entity
class MarvelPersonajesDB(

    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var name: String,
    var comic: String,
    var imagen: String
) : Parcelable