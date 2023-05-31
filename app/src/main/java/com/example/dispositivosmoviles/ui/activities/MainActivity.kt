package com.example.dispositivosmoviles.ui.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.dispositivosmoviles.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import org.w3c.dom.Text
//TU PRESENTACION
class MainActivity : AppCompatActivity() { // : significa herencia, pertenece algun layout y tiene que ser un Activity

    private lateinit var binding : ActivityMainBinding //binding, solo puede tener acceso a esta clase -> private
    //es una variable tardia -> lateinit

    @SuppressLint("MissingInflatedId")
    //onCreate, es la primera funcion que se ejecuta.
    override fun onCreate(savedInstanceState: Bundle?) { // convierte todo el metodo en objeto - layout
        super.onCreate(savedInstanceState) //reinscribir la funcion super de herencia superior

        binding = ActivityMainBinding.inflate(layoutInflater) //se crea automaticamente, tipos, propiedades y obtiene la variable
        //layoutInflater es una funcion, infla el MainActivity polimerizacion
        setContentView(binding.root)

        //initClass()


        //--------------------------------------------------------------------------------------------------------

        //setContentView(R.layout.activity_main) //levarlo a objeto - layout (debe tener el nombre y el activity llamado)
        //R es la clase de Recursos,(todo lo que sea del directorio res/.) tiene todos los ID recursos de la carpeta res.
        //var button5 = findViewById<TextView>(R.id.button5)
        //var textBuscar = findViewById<TextView>(R.id.textBuscar)

        /*
        button5.text = "INGRESAR" //inferencia de tipos del objeto botton si se cambia el tipo de dato
        button5.editableText.clear()
        button5.setOnClickListener{//al hacer click se ejecuta, es un evento
        //Un evento es una accion que se ejecuta o ejecuta ciertos pasos de lo que se genere, para obtener un resultado.
        //serie de pasos que se ejecuta una accion.
            textBuscar.text = "El evento se ha ejecutado"
            //this, nombredetipo, default.
            //es un argumento de salida (advertencia)
            Toast.makeText( //Toast no es un componente visual
                this, //es conocer de manera previa todo lo que esta sucediendo, conocer objetos, instancias, variables, ciclo de vida, etc. -> Activity, conoce todo
                "Hola XD", //mensaje
                Toast.LENGTH_SHORT)
                .show()
            var f = Snackbar.make(
                button5, //activity -> contexto -> tu Objeto
                "Es otro mensaje XD",
                Snackbar.LENGTH_LONG)
                //f.setBackgroundTint() -> colocar el color.
            f.show() // finalizar para mostrar
        }
         */
    }

    //para iniciar
    override fun onStart(){
        super.onStart()
        initClass()
    }

    //override fun onDestroy() {
    //    super.onDestroy() // finaliza la aplicacion
    //}

    private fun initClass(){
        //definir explicitamente el nombre de dato vista a objeto
        //ahorro de crear variables
        binding.button5.setOnClickListener{
            binding.textBuscar.text = "Codigo Binding presenta xdd"
            //suma != No mezclar con otras clases
            var f = Snackbar.make(
                binding.button5,
                "Mensaje desde binding XD",
                Snackbar.LENGTH_LONG)
            //f.setBackgroundTint() -> colocar el color.
            f.show() // finalizar para mostrar
        }
    }
    //private fun tuNombreCoherente(){
    //
    //}
}