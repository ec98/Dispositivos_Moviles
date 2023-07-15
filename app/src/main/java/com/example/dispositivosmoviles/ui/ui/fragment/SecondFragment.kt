package com.example.dispositivosmoviles.ui.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dispositivosmoviles.databinding.FragmentSecondBinding
import com.example.dispositivosmoviles.ui.logic.data.marvelCharacters
import com.example.dispositivosmoviles.ui.logic.marvel_logic.MarvelLogic
import com.example.dispositivosmoviles.ui.ui.fragment.adapters.MarvelAdapterv2
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding
    private lateinit var lmanager: LinearLayoutManager

    private lateinit var rvAdapter: MarvelAdapterv2

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

    override fun onStart() {
        super.onStart()
        binding.editTextF2.setOnEditorActionListener { textView, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val inputMethodManager =
                    requireContext()
                        .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(
                    binding.editTextF2.windowToken, 0
                )

                chargeDataRV(textView.text.toString())

                return@setOnEditorActionListener true
            }
            false
        }
    }

    private fun chargeDataRV(search: String) {

        lifecycleScope.launch(Dispatchers.Main) {
            marvelCharsItems = withContext(Dispatchers.IO) {
                return@withContext (MarvelLogic().getMarvelChars(
                    search, 10
                ))
            }
            rvAdapter = MarvelAdapterv2(
                marvelCharsItems
            )
            binding.recyclerF2.apply {
                this.adapter = rvAdapter
                this.layoutManager = lmanager
            }
        }
    }
}