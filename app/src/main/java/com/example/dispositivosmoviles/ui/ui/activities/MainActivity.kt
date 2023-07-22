package com.example.dispositivosmoviles.ui.ui.activities

import android.Manifest
import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.location.Geocoder
import android.os.Bundle
import android.speech.RecognizerIntent
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.dispositivosmoviles.R
import com.example.dispositivosmoviles.databinding.ActivityMainBinding
import com.example.dispositivosmoviles.ui.data.validator.LoginValidator
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import java.util.Locale
import java.util.UUID

//es una extension general; es una mini base de datos (clave-valor)
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")


class MainActivity : AppCompatActivity() {

    //location user
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    private lateinit var binding: ActivityMainBinding
    // (Lateinit) un gran poder conlleva una gran responsabilidad

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //se inicializa con un contexto this
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
    }

    override fun onStart() {
        super.onStart()
        initClass()
        //val db = DispositivosMoviles.getDbInstance()
        //db.marvelDao()
    }

    @SuppressLint("ResourceType", "MissingPermission")
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
                    binding.textView2, "Usuario o contraseña inválidos", Snackbar.LENGTH_LONG
                ).setBackgroundTint(Color.BLACK).show()
            }
        }

        val locationContract =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { itGranted ->
                when (itGranted) {
                    true -> {
                        //accedamos a la ubicacion del usuario
                        val task = fusedLocationProviderClient.lastLocation
                        task.addOnSuccessListener { itLocation ->
                            if (task.result != null) {
                                Snackbar.make(
                                    binding.txtUser,
                                    "${itLocation.latitude}, ${itLocation.longitude}",
                                    Snackbar.LENGTH_LONG
                                )
                                    .show()
                            } else {
                                Snackbar.make(
                                    binding.txtUser,
                                    "Encienda el gps, por favor oe!",
                                    Snackbar.LENGTH_LONG
                                ).show()
                            }
                            //                            .show()
                            val a = Geocoder(this)
                            a.getFromLocation(itLocation.latitude, itLocation.longitude, 1)
                        }
//                        Snackbar.make(binding.textView2, "Permiso concedido", Snackbar.LENGTH_LONG)
                    }
                    //solicitar permiso por si el usuario deniega
                    shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) -> {
                        Snackbar.make(
                            binding.textView2,
                            "Ayude con el permiso porfa",
                            Snackbar.LENGTH_LONG
                        )
                            .show()
                    }

                    false -> {
                        Snackbar.make(binding.textView2, "Denegado", Snackbar.LENGTH_LONG)
                            .show()
                    }
                }
            }

        binding.btnSearch.setOnClickListener {
            //da un aproximado de localizacion
//            locationContract.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
            //da el aproximado exacto de localizacion
            locationContract.launch(Manifest.permission.ACCESS_FINE_LOCATION)


            //solo estoy haciendo que me abra
            //no especifico un punto de llegada.
            /*
            val intent = Intent(
                Intent.ACTION_VIEW,
                //Uri.parse("http://www.google.com.ec") //buscador de google
                //con geo: se puede mandar la latitud y longitud de una pos del mapa
                //Uri.parse("geo:-0.093622,-78.4403936") //buscador de un mapa
                //Uri.parse("https://www.dota2.com/leaderboards/?l=spanish#americas") //dota rankeds
                Uri.parse("tel:0123456789") //telefono
            )
             */
//            val query = Intent(
//                Intent.ACTION_WEB_SEARCH //busqueda de web
//            )
//            query.setClassName(
//                //Los parametros para abrir una aplicacion especifica
//                "com.google.android.googlequicksearchbox",
//                "com.google.android.googlequicksearchbox.SearchActivity"
//                //manda parametros que necesita una accion de lanzamiento
//            )
//            //es un query que permite buscar algo determinado
//            query.putExtra(SearchManager.QUERY, "UCE")
//            startActivity(query)
        }

        //el lanzamiento del activity
        val appResulLocal = registerForActivityResult(StartActivityForResult()) { resultActivity ->
            val sn = Snackbar.make(
                binding.textView6, "", Snackbar.LENGTH_LONG
            )

            var message = when (resultActivity.resultCode) {
                RESULT_OK -> {
                    sn.setBackgroundTint(resources.getColor(R.color.color_verde))
                    resultActivity.data?.getStringExtra("result").orEmpty()
                }

                RESULT_CANCELED -> {
                    sn.setBackgroundTint(resources.getColor(R.color.rojo_pasion))
                    resultActivity.data?.getStringExtra("result").orEmpty()
                }

                else -> {
                    "Resultado Erroneo"
                }
//                RESULT_OK -> {
//                    Snackbar.make(
//                        binding.textView2,
//                        "Resultado Exitoso",
//                        Snackbar.LENGTH_LONG
//                    ).show()
//                }
//
//                RESULT_CANCELED -> {
//                    Snackbar.make(
//                        binding.textView3,
//                        "Resultado Cancelado",
//                        Snackbar.LENGTH_LONG
//                    ).show()
//                }
//
//                else -> {
//                    Snackbar.make(
//                        binding.textView6,
//                        "Resultado Erroneo",
//                        Snackbar.LENGTH_LONG
//                    ).show()
//                }
            }
            sn.setText(message)
            sn.show()
        }

        val speechToText = registerForActivityResult(StartActivityForResult()) { activityResult ->
            val sn = Snackbar.make(
                binding.textView6, "", Snackbar.LENGTH_LONG
            )
            var message = ""
            //recuperar algo
            when (activityResult.resultCode) {
                RESULT_OK -> {
                    message =
                        activityResult.data?.getStringArrayListExtra(
                            RecognizerIntent.EXTRA_RESULTS
                        )?.get(0).toString()
                    if (message.isNotEmpty()) {
                        val query = Intent(
                            Intent.ACTION_WEB_SEARCH //busqueda de web
                        )
                        query.setClassName(
                            //Los parametros para abrir una aplicacion especifica
                            "com.google.android.googlequicksearchbox",
                            "com.google.android.googlequicksearchbox.SearchActivity"
                            //manda parametros que necesita una accion de lanzamiento
                        )
                        //es un query que permite buscar algo determinado
                        Log.d("UCE", "Entrando al $message")
                        query.putExtra(SearchManager.QUERY, message)
                        startActivity(query)
//                        sn.setBackgroundTint(resources.getColor(R.color.verde_color))
                    }
                }

                RESULT_CANCELED -> {
                    message = "Proceso cancelado"
                    sn.setBackgroundTint(resources.getColor(R.color.rojo_pasion))
                }

                else -> {
                    message = "Proceso erroneo"
                }
            }
            sn.setText(message)
            sn.show()
        }

        binding.btnResult.setOnClickListener {
            //recupero el resultado dependiendo del resultado
//            val resIntent = Intent(this, ResultActivity::class.java)
//            appResulLocal.launch(resIntent)

            //nuevo intent
            val intentSteech =
                Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH) //conversion de voz a texto.
            //paquete y clase -> RecognizeIntent
            intentSteech.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL, //CLAVE
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM //VALOR
            )
            intentSteech.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault()
            )
            intentSteech.putExtra(
                RecognizerIntent.EXTRA_PROMPT, "Habla oe..."
            )
            speechToText.launch(intentSteech)
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