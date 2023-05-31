package com.example.dispositivosmoviles.ui.activities

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
        //miClass()

        //Nueva clase
        var name: String = ""
        intent.extras.let {
            name = it?.getString("var1")!!
        }

        Log.d("UCE", "Hello ${name}")
        binding.textViewAp.text = "Welcome " + name.toString()


        //control de errores
        Log.d("UCE", "Entrando a Start")
        binding.buttonAcp.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    MainActivity::class.java
                )
            )
        }
    }

    /*
    private fun miClass(){
        binding.textView.text = "tu contenido"

    }
     */

}