package com.example.dispositivosmoviles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import com.example.dispositivosmoviles.databinding.ActivityOtherBinding

class OtherActivity : AppCompatActivity() {

    private lateinit var binding : ActivityOtherBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other)

        binding = ActivityOtherBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        miClass()
    }

    private fun miClass(){
        binding.textView.text = "tu contenido"
    }

}