package com.example.dispositivosmoviles.ui.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dispositivosmoviles.R
import com.example.dispositivosmoviles.databinding.FragmentFirstBinding
import com.example.dispositivosmoviles.ui.logic.listas.listasItems
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
<<<<<<< HEAD
        val names = arrayListOf<String>(
            "Sofia",
            "Andrea",
            "Carla",
            "Ninett",
            "Monica"
        )
        val adapter = ArrayAdapter<String>(
            requireActivity(),
            R.layout.simple_layout,
            names
        )
//        binding.spinner.adapter = adapter
//        binding.listView.adapter=adapter
        val rvAdapter = MarvelAdapter(listasItems().returnMarvelCharacters())
=======
        val list=arrayListOf<String>(
            "Carlos",
            "Xavier",
            "Pepe",
            "Andres",
            "Mariano"
        )
        //android.R.layout.simple_layout
        val adapter= ArrayAdapter<String>(
            requireActivity(),
            R.layout.simple_layout ,
            list)
        binding.spinner.adapter=adapter
//        binding.listView.adapter=adapter
>>>>>>> 6d54c130734a6e600c7450d0e6141b50df293191

        val rvMarvel = binding.rvMarvelPersonajes
        rvMarvel.adapter = rvAdapter
        //linearLayoutManager
        rvMarvel.layoutManager = LinearLayoutManager(
            requireActivity(),
            LinearLayoutManager.VERTICAL, //manejas tu direccion
            false
        )
    }
}