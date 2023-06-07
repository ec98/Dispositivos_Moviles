package com.example.dispositivosmoviles.ui.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.dispositivosmoviles.R
import com.example.dispositivosmoviles.databinding.ActivityPrincipalBinding
import com.google.android.material.snackbar.Snackbar

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

        //No compila si descomenta intent.extras?.let{name = it.getString("var1") ?: ""}
        //intent.extras?.let {
        //    name = it.getString("var1") ?: ""
        //}

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
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.inicio -> {
                    var suma = 0
                    for (i in 1..10) {
                        suma += i
                    }
                    Snackbar.make(
                        binding.textView,
                        "Suma (1..10) = ${suma}",
                        Snackbar.LENGTH_SHORT
                    ).show()
                    true
                }

                R.id.favoritos -> {
                    var suma = 0
                    for (i in listOf(6, 9, 1)) {
                        suma += i
                    }
                    Snackbar.make(
                        binding.textView,
                        "listaOf(6,9,1) = ${suma}",
                        Snackbar.LENGTH_SHORT
                    ).show()
                    true
                }

                R.id.chat -> {
                    Snackbar.make(binding.textView, "Bienvenido a Chat GPT", Snackbar.LENGTH_SHORT)
                        .show()
                    true
                }

                else -> false
            }
        }
    }

}