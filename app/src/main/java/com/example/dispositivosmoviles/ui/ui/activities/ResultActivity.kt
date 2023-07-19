package com.example.dispositivosmoviles.ui.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dispositivosmoviles.R
import com.example.dispositivosmoviles.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        binding.btnResultOk.setOnClickListener {
            val x = Intent()
            //llamar al mismo llamado el result.
            x.putExtra("result", "Resultado Exitoso")
//          Log.d("UCE", "Entrando al resultado Ok!")
            setResult(RESULT_OK, x)
            //la matamos xd
//            onDestroy()
            finish()
        }
        binding.btnFalse.setOnClickListener {
            val x = Intent()
            //llamar al mismo llamado el result.
            x.putExtra("result", "Resultado Fallido")
//            Log.d("UCE", "Entrando al false!")
            setResult(RESULT_CANCELED, x)
//            onDestroy()
            finish()
        }
    }

//    override fun onDestroy() {
//        super.onDestroy()
//        finish()
//    }
}