package com.example.dispositivosmoviles.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dispositivosmoviles.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        initClass()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun initClass(){
        binding = ActivityMainBinding.inflate(layoutInflater) //crea elementos asociados a layout
        setContentView(binding.root)

        binding.button5.setOnClickListener{
            //INTENT
            var intent = Intent(
                this,
                PrincipalActivity::class.java
            )
            intent.putExtra("var1",binding.textView3.text.toString()) //nombre igual a la otra activity y el texto referente

            //creamos el startActivity
            startActivity(intent)
        }
    }
}