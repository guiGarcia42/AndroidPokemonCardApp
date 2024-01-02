package com.example.guilhermegarcia_rm87192.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.guilhermegarcia_rm87192.R
import com.example.guilhermegarcia_rm87192.databinding.ActivityListaHabilidadesBinding
import com.example.guilhermegarcia_rm87192.model.Pokemon
import com.example.guilhermegarcia_rm87192.util.Constants
import com.example.guilhermegarcia_rm87192.util.Constants.ACTIVITY_CARTA_POKEMON
import com.example.guilhermegarcia_rm87192.util.Constants.ACTIVITY_LISTA_HABILIDADES
import com.example.guilhermegarcia_rm87192.util.ViewUtils

class ListaHabilidadesActivity : AppCompatActivity() {

    private val binding: ActivityListaHabilidadesBinding by lazy {
        ActivityListaHabilidadesBinding.inflate(layoutInflater)
    }

    // Essa variável vai receber o pokemon da atividade principal CASO venha alguma coisa,
    // se não ela segue vazia para nós preenchermos
    private lateinit var pokemon: Pokemon

    private val habilidadeSwitches by lazy {
        listOf(
            binding.activityListaHabilidadesSwitch1,
            binding.activityListaHabilidadesSwitch2,
            binding.activityListaHabilidadesSwitch3,
            binding.activityListaHabilidadesSwitch4
        )
    }

    private val habilidadesTexts by lazy {
        // IDs dos recursos das strings das habilidades
        val habilidadeIds = listOf(
            R.string.skill1,
            R.string.skill2,
            R.string.skill3,
            R.string.skill4
        )

        habilidadeIds.map { id ->
            id to habilidadeSwitches.firstOrNull { switch ->
                switch.tag == getString(id)
            }
        }.toMap()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(Constants.temaAtual)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Método criado para resolver problema em que o layout não seguia a margem escrita no xml
        ViewUtils.aplicarMargem(binding.root, 20)

        // O ?.let no fim garante que no caso de não ser nulo a variável pokemon vai receber o valor
        intent.getParcelableExtra<Pokemon>(ACTIVITY_CARTA_POKEMON)?.let { pokemon = it }

        binding.activityListaHabilidadesBotaoAtualizar.setOnClickListener {
            Intent().apply {
                putExtra(ACTIVITY_LISTA_HABILIDADES, pokemon)
                setResult(RESULT_OK, this)
            }
            this.finish()
        }

        habilidadesTexts.forEach { (id, switch) ->
            switch?.isChecked = pokemon.habilidades.contains(getString(id))

            switch?.setOnCheckedChangeListener { _, isChecked ->
                atualizaHabilidades(isChecked, getString(id))
            }
        }

    }

    private fun atualizaHabilidades(isChecked: Boolean, habilidade: String) {
        if (isChecked && !pokemon.habilidades.contains(habilidade)) {
            pokemon.habilidades.add(habilidade)
        } else if (!isChecked) {
            pokemon.habilidades.remove(habilidade)
        }
    }
}
