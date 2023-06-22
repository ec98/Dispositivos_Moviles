package com.example.dispositivosmoviles.ui.ui.adapters

<<<<<<< HEAD
import android.graphics.Color
import android.text.Layout
=======
>>>>>>> 6d54c130734a6e600c7450d0e6141b50df293191
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dispositivosmoviles.R
import com.example.dispositivosmoviles.databinding.MarvelPersonajesBinding
<<<<<<< HEAD
import com.example.dispositivosmoviles.ui.logic.entities.marvel.marvelCharacters
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

//Recycler necesita el listado de elementos
class MarvelAdapter(private val items: List<marvelCharacters>) :
    RecyclerView.Adapter<MarvelAdapter.MarvelViewHolder>() {


    class MarvelViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding: MarvelPersonajesBinding =
            MarvelPersonajesBinding.bind(view) // binding de layout creado

        fun render(item: marvelCharacters) { //tomar cada uno de los items de la lista
            // En esta funcion se realizan cambios.
            //println("Recibiendo a ${item.nombre}")
            binding.txtName.text = item.name
            binding.txtComic.text = item.comic

            //picasso
            Picasso.get().load(item.image).into(binding.imgMarvel)

            binding.imgMarvel.setOnClickListener{
                Snackbar.make(
                    binding.imgMarvel,
                    item.name,
                    Snackbar.LENGTH_SHORT
                ).setBackgroundTint(Color.rgb(247,147,76)).show()
            }
        }
    }

    // Creacion de layout xml (element)
=======
import com.example.dispositivosmoviles.ui.logic.entities.marvel.marvelPersonajes

//Recycler necesita el listado de elementos
class MarvelAdapter(private val items: List<marvelPersonajes>) :
    RecyclerView.Adapter<MarvelAdapter.MarvelViewHolder>() {

    class MarvelViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding: MarvelPersonajesBinding =
            MarvelPersonajesBinding.bind(view) //binding de layout creado.

        fun render(item: marvelPersonajes) { //tomar cada uno de los items de la lista
//            println("Recibiendo a ${item.nombre}")
            binding.txtName.text = item.name
            binding.txtComic.text = item.comic
        }

    }

    //Creacion de layout xml (element)
>>>>>>> 6d54c130734a6e600c7450d0e6141b50df293191
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MarvelAdapter.MarvelViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MarvelViewHolder(
<<<<<<< HEAD
            inflater.inflate(
=======
            inflater.inflate( //crea una vista
>>>>>>> 6d54c130734a6e600c7450d0e6141b50df293191
                R.layout.marvel_personajes,
                parent,
                false
            )
        )
<<<<<<< HEAD
        // val a = inflater.inflate(R.layout.marvel_personajes, parent, false)
        // return MarvelViewHolder(a)
=======
//        val a = inflater.inflate(R.layout.marvel_personajes, parent, false)
//        return MarvelViewHolder(a)
>>>>>>> 6d54c130734a6e600c7450d0e6141b50df293191
    }

    override fun onBindViewHolder(holder: MarvelAdapter.MarvelViewHolder, position: Int) {
        holder.render(items[position])
    }

    override fun getItemCount(): Int = items.size

<<<<<<< HEAD

=======
>>>>>>> 6d54c130734a6e600c7450d0e6141b50df293191
}