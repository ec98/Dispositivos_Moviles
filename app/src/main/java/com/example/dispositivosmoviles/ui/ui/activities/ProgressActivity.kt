package com.example.dispositivosmoviles.ui.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.dispositivosmoviles.databinding.ActivityProgressBinding
import com.example.dispositivosmoviles.ui.ui.viewmodels.ProgressViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//MVVM
class ProgressActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProgressBinding

    //progreso vista modelo de vistas modelos.
    private val progressViewModel by viewModels<ProgressViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProgressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*
        (LiveData)
        observa cada cambio (state) -> UI
         */
        progressViewModel.progressState.observe(this, Observer {
            binding.progressBar.visibility = it //cada vez que hay un cambio se ejecuta.
        })

        progressViewModel.items.observe(this, Observer {
//            Toast.makeText(
//                this,
//                it[0].name,
//                Toast.LENGTH_SHORT
//            ).show()
            startActivity(Intent(this, NotificationActivity::class.java))
        })

        /*
        (Listeners)
        botones, funciones, etc.
         */
        binding.btnProceso.setOnClickListener {
//            progressViewModel.processBackground(3000)
            lifecycleScope.launch(Dispatchers.IO) {
                progressViewModel.getMarvelChars(0, 90)
            }
        }

        binding.btnProceso1.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                progressViewModel.getMarvelChars(0, 90)
            }
        }
    }
}