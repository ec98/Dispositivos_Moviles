package com.example.dispositivosmoviles.ui.logic.data

import android.os.Parcelable
import com.example.dispositivosmoviles.ui.data.entities.marvel.marvelData.database.MarvelPersonajesDB
import kotlinx.parcelize.Parcelize

@Parcelize // Con esta anotacion, el IDE genera los metodos necesarios
data class marvelCharacters(
    val id: Int,
    val name: String,
    val comic: String,
    val image: String
) : Parcelable

//    override fun toString(): String {
//        return "$name realizado de $comic"
//    }
fun marvelCharacters.getMarvelCharsDB(): MarvelPersonajesDB {
    return MarvelPersonajesDB(
        this.id,
        this.name,
        this.comic,
        this.image
    )
}