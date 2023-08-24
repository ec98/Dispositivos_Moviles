package com.example.dispositivosmoviles.ui.ui.fragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.dispositivosmoviles.databinding.FragmentThirtyBinding

class ThirtyFragment : Fragment() {

    private lateinit var binding: FragmentThirtyBinding
    private lateinit var galaxyWelcom: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentThirtyBinding.inflate(
            layoutInflater, container, false
        )
        galaxyWelcom = binding.imageChat.toString()
        var url1 = "https://i.pinimg.com/originals/aa/d8/9e/aad89e91106ada0dd3c0d1ae3f0104a8.gif"
        var urlparse1: Uri = Uri.parse(url1)
        Glide.with(this).load(urlparse1).into(binding.imageChat)


        return binding.root
    }

}