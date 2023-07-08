package com.piruch.edwin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.piruch.edwin.R
import com.piruch.edwin.databinding.PersonsInformBinding
import com.piruch.edwin.validator.dataPersons
import com.squareup.picasso.Picasso

class PersonAdapter(
    private var items: List<dataPersons>,
    private var fnClick: (dataPersons) -> Unit
) : RecyclerView.Adapter<PersonAdapter.PersonHolderView>() {


    class PersonHolderView(view: View) : RecyclerView.ViewHolder(view) {

        private val binding: PersonsInformBinding = PersonsInformBinding.bind(view)

        fun render(
            item: dataPersons,
            fnClick: (dataPersons) -> Unit
        ) {
            binding.nombre.text = item.nombre
            binding.asunto.text = item.asunto
            binding.cuerpo.text = item.cuerpo
            Picasso.get()
                .load(item.imagen)
                .resize(450, 450)
                .centerCrop()
                .into(binding.imageView)

            itemView.setOnClickListener { fnClick(item) }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PersonHolderView {
        val inflater = LayoutInflater.from(parent.context)
        return PersonHolderView(
            inflater.inflate(
                R.layout.persons_inform,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: PersonHolderView, position: Int) {
        holder.render(items[position], fnClick)
    }


}