package com.example.dispositivosmoviles.ui.ui.activities

import android.Manifest
import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.speech.RecognizerIntent
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.bumptech.glide.Glide
import com.example.dispositivosmoviles.R
import com.example.dispositivosmoviles.databinding.ActivityMainBinding
import com.example.dispositivosmoviles.ui.ui.Constants.Constant
import com.example.dispositivosmoviles.ui.ui.utilities.MyLocationManager
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationSettingsStatusCodes
import com.google.android.gms.location.Priority
import com.google.android.gms.location.SettingsClient
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.Locale
import java.util.UUID

//es una extension general; es una mini base de datos (clave-valor)
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // Ubicacion y GPS
    //location user
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    //locationRequest
    private lateinit var locationRequest: LocationRequest

    //locationCallback
    private lateinit var locationCallback: LocationCallback

    //variable nueva
    private var currentLocation: Location? = null

    //variables nuevas para el cliente y construccion de localizacion
    private lateinit var client: SettingsClient
    private lateinit var locationSettingRequest: LocationSettingsRequest

    //Para Firebase
    private lateinit var auth: FirebaseAuth

    //gifs
    private lateinit var galaxyWelcom: String
    private lateinit var espacio: String


    private val speechToText =
        registerForActivityResult(StartActivityForResult()) { activityResult ->
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

    @SuppressLint("MissingPermission")
    private val locationContract =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            when (isGranted) {
                true -> {

                    client.checkLocationSettings(locationSettingRequest).apply {
                        addOnSuccessListener {
                            //accedamos a la ubicacion del usuario
                            val task = fusedLocationProviderClient.lastLocation
                            task.addOnSuccessListener { itLocation ->
                                fusedLocationProviderClient.requestLocationUpdates(
                                    locationRequest,
                                    locationCallback,
                                    Looper.getMainLooper()
                                )
                            }
                        }

                        addOnFailureListener { itException ->
                            if (itException is ResolvableApiException) {
                                itException.startResolutionForResult(
                                    this@MainActivity,
                                    LocationSettingsStatusCodes.RESOLUTION_REQUIRED
                                )
                            }
                            startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
                        }
                    }

                    //mensaje de alerta
                    //aplicamos variable explicito
//                    val alert = AlertDialog.Builder(this).apply {
//                        setTitle("Notificacion")
//                        setMessage("Por favor verifique que el GPS esta activo")
//                        setPositiveButton("Verificar"){dialog,id ->
//
//                            //accediendo local, necesitamos intent para acceder
//                            val i = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
//                            startActivity(i)
//                            dialog.dismiss()
//                        }
//                        setCancelable(false)
//                    }.show()

                    //acceder a la ubicacion
//                    task.addOnSuccessListener { itLocation ->
//                        val alert = AlertDialog.Builder(
//                            this,
//                            androidx.appcompat.R.style.AlertDialog_AppCompat
//                        )
                    //aplicamos variable implicito
//                        alert.apply {
//                            setTitle("Alerta")
//                            setMessage("Existe un problema del posicionamiento global en el sistema")
//                            setPositiveButton("it's ok!") { dialog, id ->
//                                dialog.dismiss()
//                            }
//                            setCancelable(
//                                false
//                            )
////                            setNegativeButton()
//                        }.create()
                    //llamamos
//                        alert.show()
//                        //colocar la segunda funcion
//                        fusedLocationProviderClient.requestLocationUpdates(
//                            locationRequest,
//                            locationCallback,
//                            Looper.getMainLooper()
//                        )
                    //si encuentra o no
////                        if (task.result != null) {
////                            Snackbar.make(
////                                binding.txtUser,
////                                "${itLocation.latitude}, ${itLocation.longitude}",
////                                Snackbar.LENGTH_LONG
////                            )
////                                .show()
////                        } else {
////                            Snackbar.make(
////                                binding.txtUser,
////                                "Encienda el gps, por favor oe!",
////                                Snackbar.LENGTH_LONG
////                            ).show()
////                        }
////                        val a = Geocoder(this)
////                        a.getFromLocation(itLocation.latitude, itLocation.longitude, 1)
//                    }
//                    task.addOnFailureListener { itException ->
//                        if (itException is ResolvableApiException) {
//                            itException.startResolutionForResult(
//                                this@MainActivity,
//                                LocationSettingsStatusCodes.RESOLUTION_REQUIRED
//                            )
//                        }
//                    }
//                        Snackbar.make(binding.textView2, "Permiso concedido", Snackbar.LENGTH_LONG)
                }
                //solicitar permiso por si el usuario denega necio
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //gif
        galaxyWelcom = binding.presentacionImagen.toString()
        var url1 =
            "https://3.bp.blogspot.com/-4SDnliF11SQ/VRNwUV5EyfI/AAAAAAAAAH4/B5N4hjnqp3k/s1600/planeta-gif-2b924d2.gif"
        var urlparse1: Uri = Uri.parse(url1)
        Glide.with(this).load(urlparse1).into(binding.presentacionImagen)


        espacio = binding.presentacionMain.toString()
        var url2 = "https://usagif.com/wp-content/uploads/gif/outerspace-72.gif"
        var urlparse2: Uri = Uri.parse(url2)
        Glide.with(this).load(urlparse2).into(binding.presentacionMain)

        // location
        //se inicializa por un contexto this.
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        //pasive: utilizan con la red wifi (ubicacion de un lugar desconocido)
        locationRequest = LocationRequest.Builder(
            //nivel de exactitud
            Priority.PRIORITY_HIGH_ACCURACY,
            2000 //cada cuanto se va ubicar
        )
//            .setMaxUpdates(3) //actualizaciones maximas
            .build() //construir
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                if (locationResult != null) {
                    locationResult.locations.forEach { itLocation ->
                        currentLocation = itLocation
                        Log.d(
                            "UCE",
                            "Ubicacion: ${itLocation.latitude}, " + "${itLocation.longitude}"
                        )
                    }
                } else {
                    Log.d("UCE", "El GPS esta apagado")
                }
            }
        }
        //client
        client = LocationServices.getSettingsClient(this)

        //locationRequest
        locationSettingRequest =
            LocationSettingsRequest.Builder().addLocationRequest(locationRequest).build()


        //firebase
        auth = Firebase.auth

//        correo y password
        binding.btnCrear.setOnClickListener {
            authWithFireBaseEmail(
                binding.txtUser.text.toString(),
                binding.txtPassword.text.toString()
            )
        }

        binding.btnLogin.setOnClickListener {
            signInWithEmailAndPassword(
                binding.txtUser.text.toString(),
                binding.txtPassword.text.toString()
            )
        }

        binding.recuperacionCorreo.setOnClickListener {
            val i = Intent(this, RecoveryEmailPass::class.java)
            startActivity(i)
        }
    }

    //autetincando sesion
    private fun authWithFireBaseEmail(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(Constant.TAG, "createUserWithEmailAndPassword:success")
//                    val user = auth.currentUser
                    Toast.makeText(
                        baseContext,
                        "Authentication Successfull.",
                        Toast.LENGTH_SHORT,
                    ).show()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(Constant.TAG, "createUserWithEmailAndPassword:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
    }

    //iniciando sesion
    private fun signInWithEmailAndPassword(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(Constant.TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    user?.email?.let { userEmail ->
                        val intent = Intent(this, PrincipalActivity::class.java)
                        intent.putExtra("user_email", userEmail)
                        startActivity(intent)
                    }
                    Toast.makeText(
                        baseContext,
                        "Authentication Succesfull.",
                        Toast.LENGTH_SHORT,
                    ).show()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(Constant.TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
    }

    override fun onPause() {
        super.onPause()
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
    }

    override fun onStart() {
        super.onStart()
        initClass()
        //val db = DispositivosMoviles.getDbInstance()
        //db.marvelDao()
    }

    private fun initClass() {
//        binding.btnLogin.setOnClickListener {
//            val username = binding.txtUser.text.toString()
//            val password = binding.txtPassword.text.toString()
//
//            val isValid = LoginValidator().checkLogin(username, password)
//
//            if (isValid) {
//
//                //Corrutinas
//
//                val intent = Intent(this, PrincipalActivity::class.java)
//                intent.putExtra("var1", username)
//                startActivity(intent)
//            } else {
//                Snackbar.make(
//                    binding.textView2, "Usuario o contraseña inválidos", Snackbar.LENGTH_LONG
//                ).setBackgroundTint(Color.BLACK).show()
//            }
//        }

        //tw
        binding.btnSearch.setOnClickListener {
            //da un aproximado de localizacion
//            locationContract.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
            //da el aproximado exacto de localizacion
            locationContract.launch(Manifest.permission.ACCESS_FINE_LOCATION)


            //solo estoy haciendo que me abra
            //no especifico un punto de llegada.

            val intent = Intent(
                Intent.ACTION_VIEW,
                //Uri.parse("http://www.google.com.ec") //buscador de google
                //con geo: se puede mandar la latitud y longitud de una pos del mapa
                Uri.parse("geo:-0.093622,-78.4403936") //buscador de un mapa
                //Uri.parse("https://www.dota2.com/leaderboards/?l=spanish#americas") //dota rankeds
//                Uri.parse("tel:0123456789") //telefono
            )

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
            startActivity(intent)
        }
        //llamar emergencia
        binding.btnphone.setOnClickListener {

            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("tel:911") //telefono
            )
            startActivity(intent)
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


        //fb
        binding.btnResult.setOnClickListener {
            fusedLocationProviderClient.removeLocationUpdates(locationCallback)

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

    private fun test() {

        var location = MyLocationManager()
//        location.context = this
        location.getUserLocation()
    }
}