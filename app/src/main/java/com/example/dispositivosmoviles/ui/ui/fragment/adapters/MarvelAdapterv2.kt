package com.example.dispositivosmoviles.ui.ui.fragment.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dispositivosmoviles.R
import com.example.dispositivosmoviles.databinding.MarvelPersonajesBinding
import com.example.dispositivosmoviles.ui.logic.data.marvelCharacters
import com.squareup.picasso.Picasso

class MarvelAdapterv2(
    var items: List<marvelCharacters>,
) :
    RecyclerView.Adapter<MarvelAdapterv2.MarvelViewHolder>() {

    class MarvelViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding: MarvelPersonajesBinding = MarvelPersonajesBinding.bind(view)

        fun render(item: marvelCharacters) {
            binding.txtName.text = item.name
            binding.txtComic.text = item.comic
            Picasso.get().load(item.image).into(binding.imgMarvel)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MarvelAdapterv2.MarvelViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MarvelViewHolder(
            inflater.inflate(
                R.layout.marvel_personajes_v2,
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MarvelAdapterv2.MarvelViewHolder, position: Int) {
        holder.render(items[position])
    }

    override fun getItemCount(): Int = items.size
}
