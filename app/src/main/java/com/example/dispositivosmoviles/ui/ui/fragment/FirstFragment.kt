package com.example.dispositivosmoviles.ui.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dispositivosmoviles.R
import com.example.dispositivosmoviles.databinding.FragmentFirstBinding
import com.example.dispositivosmoviles.ui.data.marvel.marvelCharacters
import com.example.dispositivosmoviles.ui.logic.jikan_logic.JikanAnimeLogic
import com.example.dispositivosmoviles.ui.ui.activities.DetailsMarvelItem
import com.example.dispositivosmoviles.ui.ui.adapters.MarvelAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentFirstBinding.inflate(
            layoutInflater, container, false
        )
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val names = arrayListOf<String>(
            "Sofia",
            "Andrea",
            "Carla",
            "Ninett",
            "Monica"
        )
        //android.R.layout.simple_layout
        val adapter = ArrayAdapter<String>(
            requireActivity(),
            R.layout.simple_layout,
            names
        )
        binding.spinner.adapter = adapter
        //carga los datos
        chargeDataRV()

        binding.rvSwipe.setOnRefreshListener {
            chargeDataRV()
            binding.rvSwipe.isRefreshing = false
        }
    }

    //informacion del item seleccionado
    //serializacion -> objeto a un string. Ademas de eso, es una estructura tipo json.
    private fun sendMarvelItem(item: marvelCharacters) {
        //Los intents solo se encuentran en los fragments y en las Activities
        val i = Intent(requireActivity(), DetailsMarvelItem::class.java)
        // Esta forma de enviar informacion es ineficiente cuando se tienen mas atributos
        i.putExtra("name", item)
//        i.putExtra("name", item.name)
//        i.putExtra("comic", item.comic)
        startActivity(i)
    }

    // Serializacion: proceso de pasar de un objeto a un string para poder enviarlo por medio de la web
    // Parceables: Mucho mas eficiente que la serializacion, pues ejecutan de mejor manera el mismo proceso
    // Serializacion -> Utiliza objetos JSON
    // Parcelables   -> Son mas rapidos pero su implementacion es compleja, afortunadamente existen plugins que nos ayudan

    private fun chargeDataRV() {

        lifecycleScope.launch(Dispatchers.IO) {
            val rvAdapter = MarvelAdapter(
                JikanAnimeLogic().getAllanimes()
            ) { sendMarvelItem(it) }//entre llaves se manda los lambdas

            withContext(Dispatchers.Main) {
                with(binding.rvMarvelPersonajes) {
                    this.adapter = rvAdapter
                    this.layoutManager = LinearLayoutManager(
                        requireActivity(),
                        LinearLayoutManager.VERTICAL,
                        false
                    )
                }
            }

//        val rvAdapter = MarvelAdapter(
//            listasItems().returnMarvelCharacters(),
//        ) { sendMarvelItem(it) } //adapter sabe que es un list.
//        //so selected c/marvel
//        val rvMarvel = binding.rvMarvelPersonajes
//        with(rvMarvel) {
//            this.adapter = rvAdapter
//            this.layoutManager = LinearLayoutManager(
//                requireActivity(), LinearLayoutManager.VERTICAL, false
//            )
//        }
        }
    }
}