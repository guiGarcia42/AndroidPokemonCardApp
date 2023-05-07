package com.example.guilhermegarcia_rm87192

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pokemon(
    val name: String,
    val descricao: String,
    val skills: MutableList<String>
): Parcelable
