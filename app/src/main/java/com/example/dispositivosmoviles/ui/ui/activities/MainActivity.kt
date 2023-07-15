package com.example.dispositivosmoviles.ui.ui.activities

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.dispositivosmoviles.databinding.ActivityMainBinding
import com.example.dispositivosmoviles.ui.data.validator.LoginValidator
import com.google.android.material.snackbar.Snackbar
import java.util.UUID

//es una extension general; es una mini base de datos (clave-valor)
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    // (Lateinit) un gran poder conlleva una gran responsabilidad

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        initClass()

    }

    private fun initClass() {
        binding.btnLogin.setOnClickListener {
            val username = binding.txtUser.text.toString()
            val password = binding.txtPassword.text.toString()

            val isValid = LoginValidator().checkLogin(username, password)

            if (isValid) {

                //Corrutinas

                val intent = Intent(this, PrincipalActivity::class.java)
                intent.putExtra("var1", username)
                startActivity(intent)
            } else {
                Snackbar.make(
                    binding.textView2,
                    "Usuario o contraseña inválidos",
                    Snackbar.LENGTH_LONG
                ).setBackgroundTint(Color.BLACK).show()
            }
        }
    }

    //crear una funcion de guardar la data store
    private suspend fun saveDataStore(stringData: String) {
        dataStore.edit {//funcion suspendida
            //ejecutarse dentro de una corrutina suspend!
                preferes ->
//          preferes[stringPreferencesKey("usuario")] = binding.txtUser.text.toString()
            preferes[stringPreferencesKey("user")] = stringData
            preferes[stringPreferencesKey("session")] = UUID.randomUUID().toString()
            preferes[stringPreferencesKey("email")] = "ecpiruch@uce.edu.ec"
        }
    }
}