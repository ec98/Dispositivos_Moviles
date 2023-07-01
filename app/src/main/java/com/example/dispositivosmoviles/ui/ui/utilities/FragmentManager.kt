package com.example.dispositivosmoviles.ui.ui.utilities

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager


class FragmentManager {

    fun replaceFragment(
        manager: FragmentManager,
        container: Int,
        fragment: Fragment
    ) {
        with(manager.beginTransaction()) {
            replace(container, fragment)
            addToBackStack(null)
            commit()
        }

    }

    fun addFragment(manager: FragmentManager, container: Int, fragment: Fragment) {
        with(manager.beginTransaction()) {
            add(container, fragment)
            addToBackStack(null)
            commit()
        }

    }

}