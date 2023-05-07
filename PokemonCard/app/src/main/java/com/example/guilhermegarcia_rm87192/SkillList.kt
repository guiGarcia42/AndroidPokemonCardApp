package com.example.guilhermegarcia_rm87192

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import com.example.guilhermegarcia_rm87192.databinding.ActivitySkillListBinding

class SkillList : AppCompatActivity() {

    private lateinit var databind: ActivitySkillListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(Theme.currentTheme)
        databind = ActivitySkillListBinding.inflate(layoutInflater)
        setContentView(databind.root)
        var pokemon = intent.getParcelableExtra<Pokemon>("pokemon")
        val skillList = mutableListOf<String>()

        databind.switchskill1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                skillList.add(getString(R.string.skill1))
            }else {
                skillList.remove(getString(R.string.skill1))
            }
        }
        databind.switchskill2.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                skillList.add(getString(R.string.skill2))
            }else {
                skillList.remove(getString(R.string.skill2))
            }
        }
        databind.switchskill3.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                skillList.add(getString(R.string.skill3))
            }else {
                skillList.remove(getString(R.string.skill3))
            }
        }
        databind.switchskill4.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                skillList.add(getString(R.string.skill4))
            }else {
                skillList.remove(getString(R.string.skill4))
            }
        }

        if(pokemon == null){
            pokemon = Pokemon(getString(R.string.name_pokemon),getString(R.string.description_pokemon), skillList)
        }else{
            for(i in pokemon.skills){
                when {
                    i == getString(R.string.skill1) -> databind.switchskill1.isChecked = true
                    i == getString(R.string.skill2) -> databind.switchskill2.isChecked = true
                    i == getString(R.string.skill3) -> databind.switchskill3.isChecked = true
                    i == getString(R.string.skill4) -> databind.switchskill4.isChecked = true
                }
            }
        }

        databind.buttonUpdate.setOnClickListener {
//            val intent = Intent(this, SkillList::class.java)
//            intent.putExtra("pokemon", pokemon)
//            startActivity(intent)
            Intent().apply {
                intent.putExtra("pokemon", pokemon)
                setResult(RESULT_OK, this)
                // TODO: DATA RETORNANDO NULL 
            }
            this.finish()

        }

    }
}