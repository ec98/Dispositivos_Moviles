package com.example.dispositivosmoviles.ui.ui.fragment.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dispositivosmoviles.R
import com.example.dispositivosmoviles.databinding.MarvelPersonajesBinding
import com.example.dispositivosmoviles.ui.logic.data.marvelCharacters
import com.squareup.picasso.Picasso

//Recycler necesita el listado de elementos
//el adapter y el fragment manejan de la mano
class MarvelAdapter(
    private var items: List<marvelCharacters>,
    private var fnClick: (marvelCharacters) -> Unit //Este unit no te devuelve nada.
) : //adaptor recibe dos parametros.
    RecyclerView.Adapter<MarvelAdapter.MarvelViewHolder>() {


    class MarvelViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding: MarvelPersonajesBinding =
            MarvelPersonajesBinding.bind(view) // binding de layout creado

        fun render(
            item: marvelCharacters,
            fnClick: (marvelCharacters) -> Unit
        ) { //tomar cada uno de los items de la lista
            //println("Recibiendo a ${item.nombre}")
            binding.txtName.text = item.name
            binding.txtComic.text = item.comic

            //picasso
            Picasso.get().load(item.image).into(binding.imgMarvel)

            itemView.setOnClickListener {
                fnClick(item)
                //Snackbar.make(binding.imgMarvel, item.name, Snackbar.LENGTH_LONG).setBackgroundTint(Color.rgb(247, 147, 76)).show()
            }
        }
    }

    // Creacion de layout xml (element)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MarvelViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MarvelViewHolder(
            inflater.inflate(
                R.layout.marvel_personajes,
                parent,
                false
            )
        )
        // val a = inflater.inflate(R.layout.marvel_personajes, parent, false)
        // return MarvelViewHolder(a)
    }

    override fun onBindViewHolder(holder: MarvelViewHolder, position: Int) {
        holder.render(items[position], fnClick)
    }

    override fun getItemCount(): Int = items.size

    fun updateListItems(newItems: List<marvelCharacters>) {
        this.items = this.items.plus(newItems)
        notifyDataSetChanged()
    }

    fun replaceListItems(newItems: List<marvelCharacters>) {
        this.items = newItems
        notifyDataSetChanged()
    }

}