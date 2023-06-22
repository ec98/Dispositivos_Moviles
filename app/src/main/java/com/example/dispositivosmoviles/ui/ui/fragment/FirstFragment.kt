package com.example.dispositivosmoviles.ui.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dispositivosmoviles.R
import com.example.dispositivosmoviles.databinding.FragmentFirstBinding
import com.example.dispositivosmoviles.ui.logic.entities.marvel.marvelCharacters
import com.example.dispositivosmoviles.ui.logic.listas.listasItems
import com.example.dispositivosmoviles.ui.ui.activities.DetailsMarvelItem
import com.example.dispositivosmoviles.ui.ui.adapters.MarvelAdapter

class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentFirstBinding.inflate(
            layoutInflater, container, false
        )
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val names = arrayListOf<String>(
            "Sofia",
            "Andrea",
            "Carla",
            "Ninett",
            "Monica"
        )
        //android.R.layout.simple_layout
        val adapter = ArrayAdapter<String>(
            requireActivity(),
            R.layout.simple_layout,
            names
        )
        binding.spinner.adapter = adapter
        //carga los datos
        chargeDataRV()

        binding.rvSwipe.setOnRefreshListener {
            chargeDataRV()
            binding.rvSwipe.isRefreshing = false
        }
    }

    //informacion del item seleccionado
    //serializacion -> objeto a un string. Ademas de eso, es una estructura tipo json.
    private fun sendMarvelItem(item: marvelCharacters) {
        val i = Intent(requireActivity(), DetailsMarvelItem::class.java)
        i.putExtra("name", item.name)
        i.putExtra("comic", item.comic)
        startActivity(i)
    }

    private fun chargeDataRV() {
        val rvAdapter = MarvelAdapter(
            listasItems().returnMarvelCharacters(),
        ) { sendMarvelItem(it) } //adapter sabe que es un list.
        //so selected c/marvel
        val rvMarvel = binding.rvMarvelPersonajes
        with(rvMarvel) {
            this.adapter = rvAdapter
            this.layoutManager = LinearLayoutManager(
                requireActivity(), LinearLayoutManager.VERTICAL, false
            )
        }
//            rvMarvel.adapter = rvAdapter
//            //linearLayoutManager
//            rvMarvel.layoutManager = LinearLayoutManager(
//                requireActivity(),
//                LinearLayoutManager.VERTICAL, //manejas tu direccion
//                false
//            )
    }
}