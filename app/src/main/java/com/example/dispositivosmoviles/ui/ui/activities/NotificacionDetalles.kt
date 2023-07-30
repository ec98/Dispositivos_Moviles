package com.example.dispositivosmoviles.ui.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dispositivosmoviles.databinding.ActivityNotificacionDetallesBinding

class NotificacionDetalles : AppCompatActivity() {

    private lateinit var binding: ActivityNotificacionDetallesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificacionDetallesBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}