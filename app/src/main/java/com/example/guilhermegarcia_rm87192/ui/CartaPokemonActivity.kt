package com.example.guilhermegarcia_rm87192.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.guilhermegarcia_rm87192.R
import com.example.guilhermegarcia_rm87192.databinding.ActivityCartaPokemonBinding
import com.example.guilhermegarcia_rm87192.model.Pokemon
import com.example.guilhermegarcia_rm87192.util.Constants
import com.example.guilhermegarcia_rm87192.util.Constants.ACTIVITY_MAIN
import com.example.guilhermegarcia_rm87192.util.Constants.ACTIVITY_SKILL

class CartaPokemonActivity : AppCompatActivity() {

    private val binding: ActivityCartaPokemonBinding by lazy {
        ActivityCartaPokemonBinding.inflate(layoutInflater)
    }

    private var pokemon: Pokemon = Pokemon(
        getString(R.string.name),
        getString(R.string.description_text),
        mutableListOf()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(Constants.currentTheme)
        setContentView(binding.root)


        binding.activityMainBotaoTema.setOnClickListener {
            Constants.switchTheme()
            recreate()
        }

        binding.activityMainBotaoEditarHabilidades.setOnClickListener {
            val intent = Intent(this, SkillList::class.java)
            intent.putExtra(ACTIVITY_MAIN, pokemon)
            register.launch(intent)
        }
    }

    private val register = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.let { data ->
                data.getParcelableExtra<Pokemon>(ACTIVITY_SKILL)?.let { pokemon = it }

                if (pokemon.skills.isEmpty()) {
                    binding.activityMainTxtHabilidades.text = getString(R.string.skills_default)
                } else {
                    binding.activityMainTxtHabilidades.text = ""
                    for (habilidade in pokemon.skills) {
                        binding.activityMainTxtHabilidades.text =
                            "${binding.activityMainTxtHabilidades.text}> ${habilidade}\n"
                    }
                }
            }
        }
    }
}
