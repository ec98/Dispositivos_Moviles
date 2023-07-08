package com.piruch.edwin.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.piruch.edwin.databinding.ActivityDetailsPersonsDataBinding
import com.piruch.edwin.validator.dataPersons

class DetailsPersonsData() : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsPersonsDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsPersonsDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


    override fun onStart() {
        super.onStart()
        intent.extras?.let{
            var name = it.getString("name")
            var subject = it.getString("subject")
            binding.txtName.text = name
            binding.txtAsunto.text = subject
        }
    }
}