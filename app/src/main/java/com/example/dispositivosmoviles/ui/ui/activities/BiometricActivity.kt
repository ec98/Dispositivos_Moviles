package com.example.dispositivosmoviles.ui.ui.activities

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.example.dispositivosmoviles.databinding.ActivityBiometricBinding
import com.google.android.material.snackbar.Snackbar

class BiometricActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBiometricBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBiometricBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAutentication.setOnClickListener {
            autenticateBiometric()
        }
    }

    private fun autenticateBiometric() {
        if (biometric()) {

            val executor = ContextCompat.getMainExecutor(this)

            val biometricPrompt = BiometricPrompt.PromptInfo.Builder()
                .setTitle("huella gaaaaaaaaaa")
                .setSubtitle("Que no te de verguenza, pon tu huella xd")
                .setAllowedAuthenticators(BIOMETRIC_STRONG or DEVICE_CREDENTIAL)
//                .setNegativeButtonText("Cancela si te da verguenza")
                .build()

            //tratar de que los contextos sean locales
            val biometricManager = BiometricPrompt(this, executor,
                object : BiometricPrompt.AuthenticationCallback() {
                    override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                        super.onAuthenticationError(errorCode, errString)
                    }

                    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                        super.onAuthenticationSucceeded(result)
                        startActivity(
                            Intent(
                                this@BiometricActivity,
                                NotificationActivity::class.java
                            )
                        )
                    }

                    override fun onAuthenticationFailed() {
                        super.onAuthenticationFailed()
                    }
                })
            biometricManager.authenticate(biometricPrompt)
        } else {
            Snackbar.make(
                binding.btnAutentication,
                "No existe los requisistos causa xd",
                Snackbar.LENGTH_LONG
            ).show()
        }

    }


    //controla todo el acceso a dispositivo biometric
    private fun biometric(): Boolean {
        var returnValid = false
        val biometricManager = BiometricManager.from(this)
        when (biometricManager.canAuthenticate(
            BIOMETRIC_STRONG or DEVICE_CREDENTIAL
        )) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
                returnValid = true
            }

            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                returnValid = false
            }

            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                returnValid = false
            }

            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                //intent implicito
                val intentPrompt = Intent(Settings.ACTION_BIOMETRIC_ENROLL)
                intentPrompt.putExtra(
                    Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                    BIOMETRIC_STRONG or DEVICE_CREDENTIAL
                )
                startActivity(intentPrompt)
                returnValid = false
            }
        }
        return returnValid
    }
}