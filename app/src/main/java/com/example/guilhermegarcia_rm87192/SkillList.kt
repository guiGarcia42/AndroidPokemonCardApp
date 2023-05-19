package com.example.guilhermegarcia_rm87192

import android.content.Intent
import com.example.guilhermegarcia_rm87192.Constants.ACTIVITY_SKILL
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.guilhermegarcia_rm87192.Constants.ACTIVITY_MAIN
import com.example.guilhermegarcia_rm87192.databinding.ActivitySkillListBinding

class SkillList : AppCompatActivity() {

    private lateinit var databind: ActivitySkillListBinding
    private lateinit var pokemon: Pokemon
    // Essa variável vai receber o pokemon da atividade principal CASO venha alguma coisa, se não ela segue vazia para nós preenchermos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(Constants.currentTheme)
        databind = ActivitySkillListBinding.inflate(layoutInflater)
        setContentView(databind.root)

        intent.getParcelableExtra<Pokemon>(ACTIVITY_MAIN)?.let { pokemon = it }
        // O ?.let no fim garante que no caso de não ser nulo a variável pokemon vai receber o valor

        //Verifica se contem na lista e retorna true ou false, preenchendo os switches
        databind.switchskill1.isChecked = pokemon.skills.contains(getString(R.string.skill1))
        databind.switchskill2.isChecked = pokemon.skills.contains(getString(R.string.skill2))
        databind.switchskill3.isChecked = pokemon.skills.contains(getString(R.string.skill3))
        databind.switchskill4.isChecked = pokemon.skills.contains(getString(R.string.skill4))

        databind.switchskill1.setOnCheckedChangeListener { _, isChecked ->
            updatePokemonSkills(isChecked, getString(R.string.skill1))
        }
        databind.switchskill2.setOnCheckedChangeListener { _, isChecked ->
            updatePokemonSkills(isChecked, getString(R.string.skill2))
        }
        databind.switchskill3.setOnCheckedChangeListener { _, isChecked ->
            updatePokemonSkills(isChecked, getString(R.string.skill3))
        }
        databind.switchskill4.setOnCheckedChangeListener { _, isChecked ->
            updatePokemonSkills(isChecked, getString(R.string.skill4))
        }

        databind.buttonUpdate.setOnClickListener {
            Intent().apply {
                putExtra(ACTIVITY_SKILL, pokemon)
                setResult(RESULT_OK, this)
            }
            this.finish()
        }
    }

    fun updatePokemonSkills(isChecked: Boolean, skill: String){
        if (isChecked && !pokemon.skills.contains(skill)) {
            pokemon.skills.add(skill)
        } else if (!isChecked){
            pokemon.skills.remove(skill)
        }
    }
}
