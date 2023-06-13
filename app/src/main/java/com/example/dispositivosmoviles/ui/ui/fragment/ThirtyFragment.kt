package com.example.dispositivosmoviles.ui.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dispositivosmoviles.R
import com.example.dispositivosmoviles.databinding.FragmentFirstBinding
import com.example.dispositivosmoviles.databinding.FragmentThirtyBinding

class ThirtyFragment : Fragment() {

    private lateinit var binding : FragmentThirtyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentThirtyBinding.inflate(
            layoutInflater,container,false
        )
        return binding.root
    }

}