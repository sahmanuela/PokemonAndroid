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
            "https://img.pokemondb.net/artwork/charmander.jpg",
            1,
            "Charmander",
            listOf(PokemonType("Fire"))
        )
        val togepi = Pokemon(
            "https://assets.pokemon.com/assets/cms2/img/pokedex/full/175.png",
            2,
            "Togepi",
            listOf(PokemonType("Fairy"), PokemonType("Flying"))
        )
        val squirtle = Pokemon(
            "https://assets.pokemon.com/assets/cms2/img/pokedex/full/007.png",
            3,
            "Squirtle",
            listOf(PokemonType("Water"))
        )
        val eevee = Pokemon(
            "https://assets.pokemon.com/assets/cms2/img/pokedex/full/133.png",
            4,
            "Eevee",
            listOf(PokemonType("Normal"))
        )
        val vulpix = Pokemon(
            "https://assets.pokemon.com/assets/cms2/img/pokedex/full/037.png",
            5,
            "Vulpix",
            listOf(PokemonType("Fire"), PokemonType("Ice"))
        )
        val jigglypuff = Pokemon(
            "https://assets.pokemon.com/assets/cms2/img/pokedex/full/039.png",
            6,
            "Jigglypuff",
            listOf(PokemonType("Fairy"))
        )
        val snorlax = Pokemon(
            "https://assets.pokemon.com/assets/cms2/img/pokedex/full/143.png",
            7,
            "Snorlax",
            listOf(PokemonType("Normal"), PokemonType("Fighting"))
        )
        val meowth = Pokemon(
            "https://assets.pokemon.com/assets/cms2/img/pokedex/full/052.png",
            8,
            "Meowth",
            listOf(PokemonType("Normal"))
        )
        val pikachu = Pokemon(
            "https://assets.pokemon.com/assets/cms2/img/pokedex/full/025.png",
            9,
            "Pikachu",
            listOf(PokemonType("Electric"))
        )
        val mareep = Pokemon(
            "https://assets.pokemon.com/assets/cms2/img/pokedex/full/179.png",
            3,
            "Mareep",
            listOf(PokemonType("Electric"))
        )
        val pokemons = listOf(charmander, togepi, squirtle, eevee, vulpix, jigglypuff, snorlax, meowth, pikachu, mareep)

//        val pokemonsApi = PokemonRepository.listPokemons()
//        Log.d("POKEMON_API", pokemonsApi.toString())

        //Funcionalidade botões
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = PokemonAdapter(pokemons)

        val presentationButton = findViewById<Button>(R.id.presentationButton)

        val preferences = getSharedPreferences(Constantes.PREFERENCES, 0)
        val retrievedNome = preferences.getString("nome", "")

        if (retrievedNome.isNullOrEmpty()) {
            presentationButton.text = "Cadastrar Usuário";
        } else {
            presentationButton.text = "Hello $retrievedNome"
        }
        // Button: Redirect to profile page
        presentationButton.setOnClickListener {
            Log.d("MainActivity", "Botão clicado")
            val i = Intent(this, PerfilActivity::class.java)
            startActivity(i)
        }
    }
}
