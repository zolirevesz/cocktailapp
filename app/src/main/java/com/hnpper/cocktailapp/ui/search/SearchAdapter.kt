package com.hnpper.cocktailapp.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hnpper.cocktailapp.R
import com.hnpper.cocktailapp.model.Cocktail
import com.hnpper.cocktailapp.utilities.CocktailComparator
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_card.view.*

class SearchAdapter : ListAdapter<Cocktail, SearchAdapter.CocktailViewHolder>(CocktailComparator) {

    var listener: Listener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_card, parent, false)
        return CocktailViewHolder(view!!)
    }

    override fun onBindViewHolder(holder: CocktailViewHolder, position: Int) {
        val cocktail = getItem(position)
        holder.cocktail = cocktail

        Picasso.get().load(cocktail.strDrinkThumb).into(holder.ivCocktail)
        holder.tvCocktailName?.text = cocktail.strDrink
    }

    inner class CocktailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val ivCocktail: ImageView? = itemView.ivCocktail
        val tvCocktailName: TextView? = itemView.tvCocktailName

        var cocktail: Cocktail? = null

        init {
            itemView.setOnClickListener {
                cocktail?.let { listener?.onCocktailClicked(it) }
            }
        }
    }

    interface Listener {
        fun onCocktailClicked(cocktail: Cocktail)
    }

}
