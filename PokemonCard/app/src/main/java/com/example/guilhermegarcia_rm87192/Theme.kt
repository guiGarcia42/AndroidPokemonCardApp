package com.example.guilhermegarcia_rm87192

object Theme {
    var currentTheme = R.style.Theme_GuilhermeGarcia_RM87192

    private const val SCARLET = R.style.Theme_GuilhermeGarcia_RM87192
    private const val VIOLET = R.style.ThemeViolet

    fun switchTheme(){
        Theme.currentTheme = when(Theme.currentTheme){
            SCARLET -> VIOLET
            VIOLET -> SCARLET
            else -> -1
        }
    }


}