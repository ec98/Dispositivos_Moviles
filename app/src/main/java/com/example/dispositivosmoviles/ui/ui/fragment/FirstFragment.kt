package com.example.dispositivosmoviles.ui.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dispositivosmoviles.R
import com.example.dispositivosmoviles.databinding.FragmentFirstBinding
import com.example.dispositivosmoviles.ui.logic.data.marvelCharacters
import com.example.dispositivosmoviles.ui.logic.jikan_logic.JikanAnimeLogic
import com.example.dispositivosmoviles.ui.logic.marvel_logic.MarvelLogic
import com.example.dispositivosmoviles.ui.ui.activities.DetailsMarvelItem
import com.example.dispositivosmoviles.ui.ui.fragment.adapters.MarvelAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    private lateinit var lmanager: LinearLayoutManager
    private lateinit var rvAdapter: MarvelAdapter
    private lateinit var gManager: GridLayoutManager

    //    private var rvAdapter : MarvelAdapter = MarvelAdapter{sendMarvelItem(it)}
    //    private var page = 1
    private var marvelCharsItems: MutableList<marvelCharacters> = mutableListOf<marvelCharacters>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentFirstBinding.inflate(
            layoutInflater,
            container,
            false
        )

        lmanager = LinearLayoutManager(
            requireActivity(),
            LinearLayoutManager.VERTICAL,
            false
        )
        gManager = GridLayoutManager(requireActivity(), 2)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val names = arrayListOf<String>(
            "Sofia", "Andrea", "Carla", "Ninett", "Monica"
        )
        //android.R.layout.simple_layout
        val adapter = ArrayAdapter<String>(
            requireActivity(), R.layout.simple_layout, names
        )
        binding.spinner.adapter = adapter

        //carga los datos
        chargeDataRVDB(5)

        binding.rvSwipe.setOnRefreshListener {
            chargeDataRVDB(5)
            binding.rvSwipe.isRefreshing = false
        }
        binding.rvMarvelPersonajes.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (dy > 0) {
                    val v = lmanager.childCount
                    val p = lmanager.findFirstVisibleItemPosition()
                    val t = lmanager.itemCount

                    // v
                    // p es la posicion en la que esta
                    // t es el total de items
                    if ((v + p) >= t) {
                        lifecycleScope.launch((Dispatchers.IO)) {
                            val newItems = JikanAnimeLogic().getAllanimes()
//                                val x = withContext(Dispatchers.IO) {
//                                    MarvelLogic().getMarvelChars(name = "cap", page * 3)
//                                }
//                                rvAdapter.updateListItems(x)
                            withContext(Dispatchers.Main) {
                                rvAdapter.updateListItems(newItems)
                            }
                        }
                    }
                }
            }
        })

        //nos sirve para filtrar informacion
        binding.textfilder.addTextChangedListener { textfilter ->
            var newItems = marvelCharsItems.filter { items ->
                items.name.lowercase().contains(textfilter.toString().lowercase())
            }
            rvAdapter.replaceListItems(newItems)
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

//    fun corrotine() {
//        lifecycleScope.launch(Dispatchers.Main) {
//            var name = "Edwin"
//            name = withContext(Dispatchers.IO) {
//                name = "Kevin"
//                return@withContext name
//            }
//            binding.cardView.radius
//        }
//    }

    private fun chargeDataRVDBAPI(pos: Int) {

        lifecycleScope.launch(Dispatchers.Main) {
            //cambiamos a IO porque es una coneccino de dato
            marvelCharsItems = withContext(Dispatchers.IO) {
                return@withContext (MarvelLogic().getAllMarvelCharacters( //retornamos explicitamente los datos
                    0, 99
                ))
            }


//            rvAdapter = MarvelAdapter(
//                JikanAnimeLogic().getAllanimes()
////                MarvelLogic().getMarvelChars(name = search, 20)
//            ) { sendMarvelItem(it) }//entre llaves se manda los lambdas

            //llamamos al adapter de nuestra data
            rvAdapter = MarvelAdapter(marvelCharsItems, fnClick = { sendMarvelItem(it) })

            // en ves del withContext reeplazamos por binding, para optimizar codigo
            binding.rvMarvelPersonajes.apply {
                this.adapter = rvAdapter
                this.layoutManager = lmanager
            }
//            withContext(Dispatchers.Main) {
//                with(binding.rvMarvelPersonajes) {
//                    this.adapter = rvAdapter
//                    this.layoutManager = lmanager
//                    this.layoutManager = LinearLayoutManager(
//                        requireActivity(),
//                        LinearLayoutManager.VERTICAL,
//                        false
//                    )
        }
    }

    fun chargeDataRVDB(pos: Int) {

        lifecycleScope.launch(Dispatchers.Main) {
            //cambiamos a IO porque es una coneccino de dato
            marvelCharsItems = withContext(Dispatchers.IO) {
                var items = MarvelLogic().getAllMarvelCharsDB().toMutableList()

                if (items.isEmpty()) {
                    items = (MarvelLogic().getAllMarvelCharacters(0, 99))
                    MarvelLogic().insertMarvelCharstoDB(items)
                }
                return@withContext items
            }

            rvAdapter = MarvelAdapter(marvelCharsItems)
            { sendMarvelItem(it) }

            binding.rvMarvelPersonajes.apply {
                this.adapter = rvAdapter
                //lo reemplazo por otro manager
                this.layoutManager = lmanager
                gManager.scrollToPositionWithOffset(pos, 10)
            }
        }
    }
}