package com.example.guilhermegarcia_rm87192.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//A anotação indica que a classe pode ser usada para passar o objeto de um componente para o outro
// com o uso de intents
@Parcelize
data class Pokemon(
    val name: String,
    val descricao: String,
    val habilidades: MutableList<String>
): Parcelable
// Implementa a interface Parcelable para mostrar que essa classe pode ser serializada para passar
// para outro componente
