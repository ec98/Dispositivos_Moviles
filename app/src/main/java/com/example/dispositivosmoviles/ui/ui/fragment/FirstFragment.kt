package com.example.dispositivosmoviles.ui.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dispositivosmoviles.databinding.FragmentFirstBinding
import com.example.dispositivosmoviles.ui.logic.data.UserDataStore
import com.example.dispositivosmoviles.ui.logic.data.getMarvelCharsDB
import com.example.dispositivosmoviles.ui.logic.data.marvelCharacters
import com.example.dispositivosmoviles.ui.logic.jikan_logic.JikanAnimeLogic
import com.example.dispositivosmoviles.ui.logic.marvel_logic.MarvelLogic
import com.example.dispositivosmoviles.ui.ui.activities.DetailsMarvelItem
import com.example.dispositivosmoviles.ui.ui.activities.dataStore
import com.example.dispositivosmoviles.ui.ui.fragment.adapters.MarvelAdapter
import com.example.dispositivosmoviles.ui.ui.utilities.DispositivosMoviles
import com.example.dispositivosmoviles.ui.ui.utilities.Metodos
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FirstFragment : Fragment() {
    private lateinit var binding: FragmentFirstBinding
    private lateinit var lmanager: LinearLayoutManager
    private lateinit var rvAdapter: MarvelAdapter
    private lateinit var gManager: GridLayoutManager

    private var marvelCharsItems: MutableList<marvelCharacters> = mutableListOf<marvelCharacters>()

    private val limit = 9
    private var offset = 0

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

        //llamamos a la funcion
        lifecycleScope.launch(Dispatchers.Main) {
            getDataStore().collect { user ->
                Log.d("UCE", user.usuario)
                Log.d("UCE", user.email)
                Log.d("UCE", user.session)
            }
        }

        /*
        val names = arrayListOf<String>(
            "Sofia", "Andrea", "Carla", "Ninett", "Monica"
        )
        //android.R.layout.simple_layout
        val adapter = ArrayAdapter<String>(
            requireActivity(), R.layout.simple_layout, names
        )
        binding.spinner.adapter = adapter
         */

        //carga los datos
        chargeDataRVInit(offset, limit)

        binding.rvSwipe.setOnRefreshListener {
            chargeDataRV(offset, limit)
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
                        offset += limit
                    }
                }
            }
        })

        //nos sirve para filtrar informacion
        binding.editTextF1.addTextChangedListener { textfilter ->
            var newItems = marvelCharsItems.filter { items ->
                items.name.lowercase().contains(textfilter.toString().lowercase())
            }
            rvAdapter.replaceListItems(newItems)
        }
    }

    private fun sendMarvelItem(item: marvelCharacters) {
        val i = Intent(requireActivity(), DetailsMarvelItem::class.java)
        i.putExtra("name", item)
        startActivity(i)
    }

    fun saveMarvelItem(item: marvelCharacters): Boolean {
        lifecycleScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                if (DispositivosMoviles.getDbInstance().marvelDao()
                        .getOneCharacter(item.id) == null
                ) {
                    DispositivosMoviles
                        .getDbInstance()
                        .marvelDao()
                        .insertMarvelChar(listOf(item.getMarvelCharsDB()))
                }
            }
        }
        return true
    }

    private fun chargeDataRVAPI(search: String) {

        lifecycleScope.launch(Dispatchers.Main) {
            marvelCharsItems = withContext(Dispatchers.IO) {
                return@withContext (MarvelLogic().getMarvelChars( //retornamos explicitamente los datos
                    search, limit
                ))
            }
            //llamamos al adapter de nuestra data
            rvAdapter = MarvelAdapter(
                marvelCharsItems,
                { sendMarvelItem(it) },
                { saveMarvelItem(it) }
            )

            // en ves del withContext reeplazamos por binding, para optimizar codigo
            binding.rvMarvelPersonajes.apply {
                this.adapter = rvAdapter
                this.layoutManager = lmanager
            }
        }
        this.offset += this.limit
    }

    fun chargeDataRV(offset: Int, limit: Int) {

        lifecycleScope.launch(Dispatchers.Main) {
            //cambiamos a IO porque es una coneccino de dato
            marvelCharsItems = withContext(Dispatchers.IO) {
                return@withContext MarvelLogic().getAllMarvelCharacters(offset, limit)
            }
            rvAdapter = MarvelAdapter(
                marvelCharsItems,
                { sendMarvelItem(it) },
                { saveMarvelItem(it) }
            )

            binding.rvMarvelPersonajes.apply {
                this.adapter = rvAdapter
                this.layoutManager = lmanager
            }
        }
        this.offset += this.limit
    }

    fun chargeDataRVInit(offset: Int, limit: Int) {
        if (Metodos().isOnline(requireActivity())) {
            lifecycleScope.launch(Dispatchers.Main) {
                MarvelLogic().getInitChar(offset, limit)
                marvelCharsItems = withContext(Dispatchers.IO) {
                    MarvelLogic().getInitChar(offset, limit)
                    //Lo que estaba aqui se debe poner en el LOGIC, Saludos
                }
            }
            rvAdapter = MarvelAdapter(
                marvelCharsItems,
                { sendMarvelItem(it) },
                { saveMarvelItem(it) }
            )

            //no es necesario un with contexto si hay IO en un Main (return)
            binding.rvMarvelPersonajes.apply {
                this.adapter = rvAdapter
                this.layoutManager = lmanager
            }
            this.offset += this.limit
        } else {
            Snackbar.make(binding.cardView, "No hay conexion", Snackbar.LENGTH_LONG).show()
        }
    }

    //necesitamos llamar al activity
    //NO hacer directamente al data store, por el fragment
    private fun getDataStore(): Flow<UserDataStore> {
        val user = requireActivity().dataStore.data.map {
            UserDataStore(
                usuario = it[(stringPreferencesKey("user"))].orEmpty(),
                email = it[(stringPreferencesKey("email"))].orEmpty(),
                session = it[(stringPreferencesKey("session"))].orEmpty()
            )
        }
        return user
    }
}