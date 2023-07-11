package com.example.dispositivosmoviles.ui.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dispositivosmoviles.databinding.FragmentSecondBinding
import com.example.dispositivosmoviles.ui.logic.data.marvelCharacters
import com.example.dispositivosmoviles.ui.logic.jikan_logic.JikanAnimeLogic
import com.example.dispositivosmoviles.ui.logic.marvel_logic.MarvelLogic
import com.example.dispositivosmoviles.ui.ui.activities.DetailsMarvelItem
import com.example.dispositivosmoviles.ui.ui.fragment.adapters.MarvelAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding
    private lateinit var lmanager: LinearLayoutManager
    private lateinit var rvAdapter: MarvelAdapter

    private var marvelCharsItems: MutableList<marvelCharacters> = mutableListOf<marvelCharacters>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSecondBinding.inflate(
            layoutInflater, container, false
        )
        lmanager = LinearLayoutManager(
            requireActivity(),
            LinearLayoutManager.VERTICAL,
            false
        )
        return binding.root
    }

    private fun sendMarvelItem(item: marvelCharacters) {
        val i = Intent(requireActivity(), DetailsMarvelItem::class.java)
        i.putExtra("name", item)
        startActivity(i)
    }
    override fun onStart() {
        super.onStart()
        //carga los datos
//        chargeDataRV("cap")

        binding.rvSwipe.setOnRefreshListener {
//            chargeDataRV("cap")
            binding.rvSwipe.isRefreshing = false
        }
        binding.rvMarvelChars.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (dy > 0) {
                    val v = lmanager.childCount
                    val p = lmanager.findFirstVisibleItemPosition()
                    val t = lmanager.itemCount
                    if ((v + p) >= t) {
                        lifecycleScope.launch((Dispatchers.IO)) {
                            val newItems = JikanAnimeLogic().getAllanimes()
                            withContext(Dispatchers.Main) {
                                rvAdapter.updateListItems(newItems)
                            }
                        }
                    }
                }
            }
        })

        //nos sirve para filtrar informacion
        binding.textfilder.addTextChangedListener {
            chargeDataRV(binding.textfilder.text.toString())
        }
    }

    private fun chargeDataRV(search: String) {

        lifecycleScope.launch(Dispatchers.Main) {
            marvelCharsItems = withContext(Dispatchers.IO) {
                return@withContext (MarvelLogic().getMarvelChars(
                    search, 99
                ))
            }
            rvAdapter = MarvelAdapter(marvelCharsItems, fnClick = { sendMarvelItem(it) })
            binding.rvMarvelChars.apply {
                this.adapter = rvAdapter
                this.layoutManager = lmanager
            }
        }
    }
}