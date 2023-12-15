package com.android.pokemon.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.pokemon.R
import com.android.pokemon.domain.Pokemon
import com.android.pokemon.domain.PokemonType


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.rvPokemons)
        val layoutManager = LinearLayoutManager(this)

        val charmander = Pokemon(
            "https://img.mypcards.com/img/2/1352/pokemon_viv_023/pokemon_viv_023_pt.jpg",
            1,
            "Charmander",
            listOf(PokemonType("Fire"))
        )
        val pokemons = listOf(charmander, charmander, charmander, charmander, charmander)

//        val pokemonsApi = PokemonRepository.listPokemons()
//        Log.d("POKEMON_API", pokemonsApi.toString())

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = PokemonAdapter(pokemons)

        val presentationButton = findViewById<Button>(R.id.presentationButton)

        // Button: Redirect to profile page
        presentationButton.setOnClickListener {
            Log.d("MainActivity", "Botão clicado")
            val i = Intent(this, PerfilActivity::class.java)
            startActivity(i)
        }
    }
}
