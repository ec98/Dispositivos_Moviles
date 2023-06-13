package com.example.dispositivosmoviles.ui.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.dispositivosmoviles.R
import com.example.dispositivosmoviles.databinding.ActivityPrincipalBinding
import com.example.dispositivosmoviles.ui.ui.fragment.FirstFragment
import com.example.dispositivosmoviles.ui.ui.fragment.SecondFragment
import com.example.dispositivosmoviles.ui.ui.fragment.ThirtyFragment
import com.example.dispositivosmoviles.ui.ui.utilities.FragmentManager
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
                    FragmentManager().replaceFragment(
                        supportFragmentManager,
                        binding.framContainer.id,
                        FirstFragment()
                    )
//                    val frag = FirstFragment()
//                    val transaction = supportFragmentManager.beginTransaction()
//                    transaction.add(binding.framContainer.id,frag)
//                    transaction.addToBackStack(null)
//                    transaction.commit()
                    true
                }

                R.id.favoritos -> {
//                    var suma = 0
//                    for (i in listOf(6, 9, 1)) {
//                        suma += i
//                    }
//                    Snackbar.make(
//                        binding.textView,
//                        "listaOf(6,9,1) = ${suma}",
//                        Snackbar.LENGTH_SHORT
//                    ).show()
                    // Respond to navigation item 2 click

                    FragmentManager().replaceFragment(
                        supportFragmentManager,
                        binding.framContainer.id,
                        SecondFragment()
                    )
                    true
                }

                R.id.chat -> {
//                    Snackbar.make(binding.textView, "Bienvenido a Chat GPT", Snackbar.LENGTH_SHORT)
//                        .show()
                    // Respond to navigation item 3 click
                    var frag = ThirtyFragment();
                    val trans = supportFragmentManager.beginTransaction()
                    trans.replace(binding.framContainer.id, frag)
                    trans.addToBackStack(null)
                    trans.commit()
                    true
                }

                else -> false
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

}