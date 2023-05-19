package com.example.guilhermegarcia_rm87192

object Constants {
    const val ACTIVITY_SKILL = "Activity Skills"
    const val ACTIVITY_MAIN = "Activity Main"

    var currentTheme = R.style.Theme_GuilhermeGarcia_RM87192

    private const val SCARLET = R.style.Theme_GuilhermeGarcia_RM87192
    private const val VIOLET = R.style.ThemeViolet

    fun switchTheme(){
        Constants.currentTheme = when(Constants.currentTheme){
            SCARLET -> VIOLET
            VIOLET -> SCARLET
            else -> -1
        }
    }


}