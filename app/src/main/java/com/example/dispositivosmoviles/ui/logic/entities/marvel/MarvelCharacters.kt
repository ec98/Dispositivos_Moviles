package com.example.dispositivosmoviles.ui.logic.entities.marvel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class marvelCharacters(
    val id: Int,
    val name: String,
    val comic: String,
    val image: String
) : Parcelable {
    override fun toString(): String {
        return "$name realizado de $comic"
    }
}