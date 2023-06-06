package com.example.dispositivosmoviles.ui.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.dispositivosmoviles.R
import com.example.dispositivosmoviles.databinding.ActivityPrincipalBinding

class PrincipalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPrincipalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        Log.d("UCE", "Entrando a Create")
        binding = ActivityPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        var name: String = ""
        intent.extras?.let {
            name = it.getString("var1") ?: ""
        }

        Log.d("UCE", "Hello $name")
        binding.textView.text = "Welcome $name!"

        Log.d("UCE", "Entrando a Start")
        initClass()
    }

    private fun initClass() {
        binding.imageButton2.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    MainActivity::class.java
                )
            )
        }
    }

}