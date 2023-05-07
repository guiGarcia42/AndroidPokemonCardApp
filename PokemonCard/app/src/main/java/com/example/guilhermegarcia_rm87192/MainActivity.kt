package com.example.guilhermegarcia_rm87192

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.guilhermegarcia_rm87192.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var databind: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(Theme.currentTheme)
        databind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(databind.root)
        var pokemon = intent.getParcelableExtra<Pokemon>("pokemon")

        val register = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.let { data ->
                    val pokemonExtra = data.getParcelableExtra<Pokemon>("POKEMON")

                    pokemonExtra?.let {
                        if (pokemon?.skills != null) {
                            var skills: String = ""
                            for (i in pokemon.skills) {
                                skills += i
                                skills += "\n"
                            }
                            databind.skill.text = skills
                        }
                    }

                }
            }
        }

        if (pokemon?.skills != null) {
            var skills: String = ""
            for (i in pokemon.skills) {
                skills += i
                skills += "\n"
            }
            databind.skill.text = skills
        }

        databind.buttonTheme.setOnClickListener {
            Theme.switchTheme()
            recreate()
        }

        databind.buttonEdit.setOnClickListener {
            val intent = Intent(this, SkillList::class.java)
            intent.putExtra("pokemon", pokemon)
            register.launch(intent)
        }
    }
}
