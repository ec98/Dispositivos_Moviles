package com.example.dispositivosmoviles.ui.logic.listas

import com.example.dispositivosmoviles.ui.logic.entities.LoginUser
import com.example.dispositivosmoviles.ui.logic.entities.marvel.marvelPersonajes

class ListItems {

    fun returnItems(): List<LoginUser> {
        var items = listOf<LoginUser>(
            LoginUser("1", "1"),
            LoginUser("2", "1"),
            LoginUser("3", "1"),
            LoginUser("4", "1"),
            LoginUser("5", "1")
        )
        return items
    }

    fun returnMarvelPersons(): List<marvelPersonajes> {
        val items = listOf(
            marvelPersonajes(
                1,
                "Spiderman",
                "The Amazing Spider-Man",
                "https://comicvine.gamespot.com/a/uploads/scale_small/12/124259/8126579-amazing_spider-man_vol_5_54_stormbreakers_variant_textless.jpg"
            ),
            marvelPersonajes(
                2,
                "Wolverine",
                "Gli Incredibili X-men",
                "https://comicvine.gamespot.com/a/uploads/scale_small/5/57023/7469590-wolverinerb.jpg"
            ),
            marvelPersonajes(
                3,
                "Thor",
                "The Avengers",
                "https://comicvine.gamespot.com/a/uploads/scale_small/12/124259/8632242-rco009_1653501316.jpg"
            ),
            marvelPersonajes(
                4,
                "Iron man",
                "The invencible iron man",
                "https://comicvine.gamespot.com/a/uploads/scale_small/12/124259/8654427-ezgif-1-2f113089e4.jpg"
            ),
            marvelPersonajes(
                5,
                "Elektra",
                "Daredevil",
                "https://comicvine.gamespot.com/a/uploads/scale_small/12/124259/8324004-daredevil_woman_without_fear_vol_1_1_unknown_comic_books_exclusive_virgin_variant.jpg"
            ),

            )
        return items
    }
}