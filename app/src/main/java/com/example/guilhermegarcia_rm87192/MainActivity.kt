package com.example.guilhermegarcia_rm87192

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.guilhermegarcia_rm87192.Constants.ACTIVITY_MAIN
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.guilhermegarcia_rm87192.Constants.ACTIVITY_SKILL
import com.example.guilhermegarcia_rm87192.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var databind: ActivityMainBinding
    private lateinit var pokemon: Pokemon

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(Constants.currentTheme)
        databind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(databind.root)

        pokemon = Pokemon(
            getString(R.string.name_pokemon),
            getString(R.string.description_pokemon),
            mutableListOf()
        )

        databind.buttonTheme.setOnClickListener {
            Constants.switchTheme()
            recreate()
        }

        databind.buttonEdit.setOnClickListener {
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
                    databind.skill.text = getString(R.string.skills_default)
                } else {
                    databind.skill.text = ""
                    for (skill in pokemon.skills) {
                        databind.skill.text = "${databind.skill.text}> ${skill}\n"
                    }
                }
            }
        }
    }
}
