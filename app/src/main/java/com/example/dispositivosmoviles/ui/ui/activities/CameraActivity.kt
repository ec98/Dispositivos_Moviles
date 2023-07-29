package com.example.dispositivosmoviles.ui.ui.activities

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.appcompat.app.AppCompatActivity
import com.example.dispositivosmoviles.databinding.ActivityCameraBinding
import com.google.android.material.snackbar.Snackbar

class CameraActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCameraBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCapture.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            cameraResult.launch(intent)
        }

        binding.imgCapture.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_VIEW)
            shareIntent.putExtra(
                Intent.EXTRA_TEXT,
                "Hola como estas"
            )
            shareIntent.setType("Comparte tu foto pe")

            startActivity(
                Intent.createChooser(
                    shareIntent, "Compartir"
                )
            )
        }
    }

    private val cameraResult =
        registerForActivityResult(StartActivityForResult()) { itActivityResult ->
            when (itActivityResult.resultCode) {
                Activity.RESULT_OK -> {

                    val image = itActivityResult.data?.extras?.get("data") as Bitmap
                    binding.imgCapture.setImageBitmap(image)
                }

                Activity.RESULT_CANCELED -> {
                    Snackbar.make(
                        binding.textCapture,
                        "Proceso cancelado causa :v",
                        Snackbar.LENGTH_LONG
                    ).show()
                }

                else -> {
                    Log.d("UCE", "Proceso erroneo de captura")
                }
            }
        }
}