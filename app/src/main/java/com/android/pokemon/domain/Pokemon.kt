package com.android.pokemon.domain

//Each Pokémon will receive some values: Image, number/ID, name and characteristics
data class Pokemon(
    val imageUrl: String,
    val number: Int,
    val name: String,
    val types: List<PokemonType>
)
{
    val formattedNumber = number.toString().padStart(3, '0')
}