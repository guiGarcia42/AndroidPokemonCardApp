package com.example.guilhermegarcia_rm87192.util

import com.example.guilhermegarcia_rm87192.R

object Constants {
    const val ACTIVITY_SKILL = "Activity Skills"
    const val ACTIVITY_MAIN = "Activity Main"

    var currentTheme = R.style.Theme_GuilhermeGarcia_RM87192

    private const val SCARLET = R.style.Theme_GuilhermeGarcia_RM87192
    private const val VIOLET = R.style.ThemeViolet

    fun switchTheme(){
        currentTheme = when(currentTheme){
            SCARLET -> VIOLET
            VIOLET -> SCARLET
            else -> -1
        }
    }


}