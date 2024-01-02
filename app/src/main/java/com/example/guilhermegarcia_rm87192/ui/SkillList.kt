package com.example.guilhermegarcia_rm87192.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.guilhermegarcia_rm87192.R
import com.example.guilhermegarcia_rm87192.databinding.ActivityListaHabilidadesBinding
import com.example.guilhermegarcia_rm87192.model.Pokemon
import com.example.guilhermegarcia_rm87192.util.Constants
import com.example.guilhermegarcia_rm87192.util.Constants.ACTIVITY_MAIN
import com.example.guilhermegarcia_rm87192.util.Constants.ACTIVITY_SKILL

class SkillList : AppCompatActivity() {

    private val binding: ActivityListaHabilidadesBinding by lazy {
        ActivityListaHabilidadesBinding.inflate(layoutInflater)
    }
    private lateinit var pokemon: Pokemon
    // Essa variável vai receber o pokemon da atividade principal CASO venha alguma coisa, se não ela segue vazia para nós preenchermos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(Constants.currentTheme)

        setContentView(binding.root)

        intent.getParcelableExtra<Pokemon>(ACTIVITY_MAIN)?.let { pokemon = it }
        // O ?.let no fim garante que no caso de não ser nulo a variável pokemon vai receber o valor

        //Verifica se contem na lista e retorna true ou false, preenchendo os switches
        binding.switchskill1.isChecked = pokemon.skills.contains(getString(R.string.skill1))
        binding.switchskill2.isChecked = pokemon.skills.contains(getString(R.string.skill2))
        binding.switchskill3.isChecked = pokemon.skills.contains(getString(R.string.skill3))
        binding.switchskill4.isChecked = pokemon.skills.contains(getString(R.string.skill4))

        binding.switchskill1.setOnCheckedChangeListener { _, isChecked ->
            updatePokemonSkills(isChecked, getString(R.string.skill1))
        }
        binding.switchskill2.setOnCheckedChangeListener { _, isChecked ->
            updatePokemonSkills(isChecked, getString(R.string.skill2))
        }
        binding.switchskill3.setOnCheckedChangeListener { _, isChecked ->
            updatePokemonSkills(isChecked, getString(R.string.skill3))
        }
        binding.switchskill4.setOnCheckedChangeListener { _, isChecked ->
            updatePokemonSkills(isChecked, getString(R.string.skill4))
        }

        binding.buttonUpdate.setOnClickListener {
            Intent().apply {
                putExtra(ACTIVITY_SKILL, pokemon)
                setResult(RESULT_OK, this)
            }
            this.finish()
        }
    }

    fun updatePokemonSkills(isChecked: Boolean, skill: String) {
        if (isChecked && !pokemon.skills.contains(skill)) {
            pokemon.skills.add(skill)
        } else if (!isChecked) {
            pokemon.skills.remove(skill)
        }
    }
}
