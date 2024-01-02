package com.example.guilhermegarcia_rm87192.util

import com.example.guilhermegarcia_rm87192.R

object Constants {
    const val ACTIVITY_LISTA_HABILIDADES = "Activity Lista de Habilidades"
    const val ACTIVITY_CARTA_POKEMON = "Activity Carta do Pokemon"

    var temaAtual = R.style.Theme_GuilhermeGarcia_RM87192_TemaScarlet

    private const val SCARLET = R.style.Theme_GuilhermeGarcia_RM87192_TemaScarlet
    private const val VIOLET = R.style.Theme_GuilhermeGarcia_RM87192_TemaViolet

    fun trocarTema(){
        temaAtual = when(temaAtual){
            SCARLET -> VIOLET
            VIOLET -> SCARLET
            else -> -1
        }
    }

}