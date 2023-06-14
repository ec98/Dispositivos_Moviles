package com.example.dispositivosmoviles.ui.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.dispositivosmoviles.R
import com.example.dispositivosmoviles.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private lateinit var binding : FragmentFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentFirstBinding.inflate(
            layoutInflater,container,false
        )
        return binding.root
    }

    override fun onStart() {
        super.onStart()
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

    }
}