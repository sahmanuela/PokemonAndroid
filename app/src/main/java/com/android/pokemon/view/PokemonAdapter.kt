package com.android.pokemon.view

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.pokemon.R
import com.android.pokemon.domain.Pokemon
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class PokemonAdapter(
    private val items: List<Pokemon>
) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bindView(item)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(item: Pokemon) = with(itemView) {
            val ivPokemon = findViewById<ImageView>(R.id.ivPokemon)
            val tvNumber = findViewById<TextView>(R.id.tvNumber)
            val tvName = findViewById<TextView>(R.id.tvName)
            val tvType1 = findViewById<TextView>(R.id.tvType1)
            val tvType2 = findViewById<TextView>(R.id.tvType2)

            // Cores dos tipos dos pokemóns
            val typeColors = mapOf(
                "Fire" to "#FF5722",
                "Water" to "#2196F3",
                "Ground" to "#795548",
                "Fairy" to "#fdb9e9",
                "Normal" to "#212121",
                "Electric" to "#FFC107",
                "Flying" to "#40C4FF",
                "Ice" to "#B2EBF2",
                "Fighting" to "#FF6D00"
            )

            //TODO: Load Image With Glide: Library for loading images
            Glide.with(itemView.context)
                .load(item.imageUrl)
                .apply(RequestOptions.placeholderOf(R.drawable.placeholder))
                .apply(RequestOptions.errorOf(R.drawable.error_image))
                .into(ivPokemon)

            tvNumber.text = "Nº ${item.formattedNumber}"
            tvName.text = item.name
            tvType1.text = item.types[0].name
            tvType1.setBackgroundColor(Color.parseColor(typeColors[item.types[0].name] ?: "#212121"))


//            Each pokemon can have two characteristics
//            If he only has one, hide Type2
            if(item.types.size > 1) {
                tvType2.visibility = View.VISIBLE
                tvType2.text = item.types[1].name
            } else {
                tvType2.visibility = View.GONE
            }
        }
    }
}