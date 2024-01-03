package com.example.guilhermegarcia_rm87192.util

import android.view.View
import android.view.ViewGroup

object ViewUtils {

    fun aplicarMargem(view: View, margemEmDP: Int) {
        // Conversão de px para dp
        val escalaDeDensidade: Float = view.resources.displayMetrics.density
        val margemEmPX = (margemEmDP * escalaDeDensidade + 0.5f).toInt()

        // Aplicando as margens na ViewGroup
        val params = view.layoutParams as ViewGroup.MarginLayoutParams
        params.setMargins(margemEmPX, 0, margemEmPX, margemEmPX)
        view.layoutParams = params
    }
}