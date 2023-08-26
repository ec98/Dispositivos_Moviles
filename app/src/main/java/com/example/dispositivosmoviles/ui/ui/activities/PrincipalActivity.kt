package com.example.dispositivosmoviles.ui.ui.activities

import android.animation.ValueAnimator
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.dispositivosmoviles.R
import com.example.dispositivosmoviles.databinding.ActivityPrincipalBinding
import com.example.dispositivosmoviles.ui.ui.fragment.FirstFragment
import com.example.dispositivosmoviles.ui.ui.fragment.SecondFragment
import com.example.dispositivosmoviles.ui.ui.fragment.ThirtyFragment
import com.example.dispositivosmoviles.ui.ui.utilities.FragmentManager

class PrincipalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPrincipalBinding
    private lateinit var perfilKT: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

//        Log.d("UCE", "Entrando a Create")
        binding = ActivityPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        perfilKT = binding.perfilImagen.toString()
        var url1 =
            "https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/95740abd-84e1-40f6-9ea9-9a73ce727d1a/dd1n7k5-cab0598e-9815-4771-b9a0-f9e8a47f934e.gif?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7InBhdGgiOiJcL2ZcLzk1NzQwYWJkLTg0ZTEtNDBmNi05ZWE5LTlhNzNjZTcyN2QxYVwvZGQxbjdrNS1jYWIwNTk4ZS05ODE1LTQ3NzEtYjlhMC1mOWU4YTQ3ZjkzNGUuZ2lmIn1dXSwiYXVkIjpbInVybjpzZXJ2aWNlOmZpbGUuZG93bmxvYWQiXX0.QnyCmnFtwqVsNMgShNBezrBAGTjZApJX7aaHU0amG10"
        var urlparse1: Uri = Uri.parse(url1)
        Glide.with(this).load(urlparse1).into(binding.perfilImagen)

        val targetText =
            "K.T: Esto es solo una presentacion, abajo encontraras los menus que te llevaran a otras interfaces. ^^"
        val durationPerLetter = 100L //duracion por letra

        val valueAnimator = ValueAnimator.ofInt(0, targetText.length)
        valueAnimator.duration = (durationPerLetter * targetText.length)

        valueAnimator.addUpdateListener { animator ->
            val currentText = targetText.substring(0, animator.animatedValue as Int)
            binding.presentacionText.text = currentText
        }
        valueAnimator.start()

        val userEmail = intent.getStringExtra("user_email")
        binding.user.text = "Tu correo ^^: $userEmail"

        if (userEmail != null) {
            animateText(userEmail, binding.user)
        }
    }

    private fun animateText(text: String, textView: TextView) {
        val words = text.split(" ")
        var currentIndex = 0

        Handler(mainLooper).postDelayed(object : Runnable {
            override fun run() {
                if (currentIndex < words.size) {
                    val word = words[currentIndex]
                    textView.text = textView.text.toString() + " " + word
                    currentIndex++
                    Handler(mainLooper).postDelayed(this, 500) // Ajusta el tiempo entre palabras
                }
            }
        }, 0) // Iniciar inmediatamente
    }

    override fun onStart() {
        super.onStart()

        var name: String = "ecStarkOf"

        //No compila si descomenta intent.extras?.let{name = it.getString("var1") ?: ""}
        //intent.extras?.let {
        //    name = it.getString("var1") ?: ""
        //}

//        Log.d("UCE", "Hello $name")
        binding.textView.text = "Welcome $name!"
//        Log.d("UCE", "Entrando a Start")
        initClass()
    }

    private fun initClass() {

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.inicio -> {
                    FragmentManager().replaceFragment(
                        supportFragmentManager,
                        binding.framContainer.id,
                        FirstFragment()
                    )
//                    val frag = FirstFragment()
//                    val transaction = supportFragmentManager.beginTransaction()
//                    transaction.add(binding.framContainer.id,frag)
//                    transaction.addToBackStack(null)
//                    transaction.commit()
                    true
                }

                R.id.favoritos -> {
//                    var suma = 0
//                    for (i in listOf(6, 9, 1)) {
//                        suma += i
//                    }
//                    Snackbar.make(
//                        binding.textView,
//                        "listaOf(6,9,1) = ${suma}",
//                        Snackbar.LENGTH_SHORT
//                    ).show()
                    // Respond to navigation item 2 click

                    FragmentManager().replaceFragment(
                        supportFragmentManager,
                        binding.frContainer2.id,
                        SecondFragment()
                    )
                    true
                }

                R.id.chat -> {
//                    Snackbar.make(binding.textView, "Bienvenido a Chat GPT", Snackbar.LENGTH_SHORT)
//                        .show()
                    // Respond to navigation item 3 click
                    var frag = ThirtyFragment();
                    val trans = supportFragmentManager.beginTransaction()
                    trans.replace(binding.frContainer3.id, frag)
                    trans.addToBackStack(null)
                    trans.commit()
                    true
                }

                else -> false
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }


}