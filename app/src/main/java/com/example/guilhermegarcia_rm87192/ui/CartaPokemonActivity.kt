package com.example.guilhermegarcia_rm87192.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.guilhermegarcia_rm87192.R
import com.example.guilhermegarcia_rm87192.databinding.ActivityCartaPokemonBinding
import com.example.guilhermegarcia_rm87192.model.Pokemon
import com.example.guilhermegarcia_rm87192.util.Constants
import com.example.guilhermegarcia_rm87192.util.Constants.ACTIVITY_CARTA_POKEMON
import com.example.guilhermegarcia_rm87192.util.Constants.ACTIVITY_LISTA_HABILIDADES
import com.example.guilhermegarcia_rm87192.util.ViewUtils

class CartaPokemonActivity : AppCompatActivity() {

    private val register = activityResultLauncher()
    private lateinit var pokemon: Pokemon

    private val binding: ActivityCartaPokemonBinding by lazy {
        ActivityCartaPokemonBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(Constants.temaAtual)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Método criado para resolver problema em que o layout não seguia a margem escrita no xml
        ViewUtils.aplicarMargem(binding.root, 20)

        pokemon = Pokemon(
            getString(R.string.name),
            getString(R.string.description_text),
            mutableListOf()
        )

        binding.activityCartaPokemonBotaoTema.setOnClickListener {
            Constants.trocarTema()
            recreate()
        }

    }

    override fun onResume() {
        super.onResume()

        binding.activityCartaPokemonBotaoEditarHabilidades.setOnClickListener {
            val intent = Intent(this, ListaHabilidadesActivity::class.java)
            intent.putExtra(ACTIVITY_CARTA_POKEMON, pokemon)
            register.launch(intent)
        }
    }

    private fun activityResultLauncher(): ActivityResultLauncher<Intent> {
        return registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result: ActivityResult ->

            if (result.resultCode == RESULT_OK) {
                result.data?.let { data ->
                    data.getParcelableExtra<Pokemon>(ACTIVITY_LISTA_HABILIDADES)?.let { pokemon = it }

                    if (pokemon.habilidades.isEmpty()) {
                        binding.activityCartaPokemonTxtHabilidades.text =
                            getString(R.string.skills_default)
                    } else {
                        binding.activityCartaPokemonTxtHabilidades.text = ""
                        for ((index, habilidade) in pokemon.habilidades.withIndex()) {
                            binding.activityCartaPokemonTxtHabilidades.text =
                                "${binding.activityCartaPokemonTxtHabilidades.text}> $habilidade"

                            // Adiciona quebra de linha apenas se não for o último item
                            if (index < pokemon.habilidades.size - 1) {
                                binding.activityCartaPokemonTxtHabilidades.append("\n")
                            }
                        }
                    }
                }
            }
        }
    }

}


