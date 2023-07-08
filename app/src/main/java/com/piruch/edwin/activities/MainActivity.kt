package com.piruch.edwin.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.piruch.edwin.ListItems
import com.piruch.edwin.adapters.PersonAdapter
import com.piruch.edwin.databinding.ActivityMainBinding
import com.piruch.edwin.validator.dataPersons

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        val rvAdapter = PersonAdapter(
            ListItems().getData()
        ) { sendDato(it) }

        val rvDatos = binding.rvPersonasDatas
        rvDatos.adapter = rvAdapter
        rvDatos.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )

    }

    fun sendDato(item: dataPersons) {
        val i = Intent(this, DetailsPersonsData::class.java)
        Log.d("UCE", item.toString())
        i.putExtra("name", item.nombre)
        i.putExtra("subject", item.asunto)
        startActivity(i)
    }

}