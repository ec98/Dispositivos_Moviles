package com.example.dispositivosmoviles.ui.data.entities.marvel.marvelData

data class Series(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)