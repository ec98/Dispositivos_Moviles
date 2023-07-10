package com.example.dispositivosmoviles.ui.data.entities.marvel.marvelData

import com.example.dispositivosmoviles.ui.logic.data.marvelCharacters

data class Result(
    val comics: Comics,
    val description: String,
    val events: Events,
    val id: Int,
    val modified: String,
    val name: String,
    val resourceURI: String,
    val series: Series,
    val stories: Stories,
    val thumbnail: Thumbnail,
    val urls: List<Url>
)

fun Result.getMarvelChars(): marvelCharacters {
    var comic: String = ""
    //se busca si esta mayor de 0 porque la lista esta vacia el campo de comic
    if (comics.items.isNotEmpty()) {
        comic = comics.items[0].name
    }
    val m = marvelCharacters(
        id,
        name, comic,
        thumbnail.path + "." + thumbnail.extension
    )
    return m
}