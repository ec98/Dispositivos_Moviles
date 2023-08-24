package com.example.dispositivosmoviles.ui.ui.activities

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.dispositivosmoviles.databinding.ActivityRecoveryEmailPassBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth

class RecoveryEmailPass : AppCompatActivity() {

    private lateinit var binding: ActivityRecoveryEmailPassBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var animationKT: String
    private lateinit var animationYM: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecoveryEmailPassBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.confimarCorreo.setOnClickListener {
            recoveryPasswordWithEmail(
                binding.txtCorreo.text.toString()
            )
        }
        animationKT = binding.correoAnimaction.toString()
        var url1 = "https://media.tenor.com/uEGvgTMmboUAAAAd/yugioh-yugioh-duel-monsters.gif"
        var urlparse1: Uri = Uri.parse(url1)
        Glide.with(this).load(urlparse1).into(binding.correoAnimaction)

        animationYM = binding.presentacionRecovery.toString()
        var url2 = "https://i.pinimg.com/originals/18/88/ed/1888edde5e99e42e29c830bf1bcf83e3.gif"
        var urlparse2: Uri = Uri.parse(url2)
        Glide.with(this).load(urlparse2).into(binding.presentacionRecovery)


    }

    //recuperando correo
    private fun recoveryPasswordWithEmail(email: String) {
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { itTask ->
                if (itTask.isSuccessful) {
                    Toast.makeText(
                        this,
                        "Correo de recuperacion enviado correcto",
                        Toast.LENGTH_LONG
                    ).show()
                    MaterialAlertDialogBuilder(this).apply {
                        setTitle("Alerta")
                        setMessage("Su correo de recuperacion ha sido procesado correcto")
                        setCancelable(true)
                        setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                    }.show()
                }
            }
    }
}