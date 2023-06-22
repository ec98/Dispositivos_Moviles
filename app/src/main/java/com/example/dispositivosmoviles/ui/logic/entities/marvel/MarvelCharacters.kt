package com.example.dispositivosmoviles.ui.logic.entities.marvel

data class marvelCharacters(val id: Int, val name: String, val comic: String, val image: String) {
    override fun toString(): String {
        return "$name realizado de $comic"
    }
}