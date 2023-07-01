package com.example.dispositivosmoviles.ui.data.entities.marvel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize // Con esta anotacion, el IDE genera los metodos necesarios
data class marvelCharacters(
    val id: Int,
    val name: String,
    val comic: String,
    val image: String
) : Parcelable {
//    override fun toString(): String {
//        return "$name realizado de $comic"
//    }
}