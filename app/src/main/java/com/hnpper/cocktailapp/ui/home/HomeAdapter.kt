package com.hnpper.cocktailapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hnpper.cocktailapp.R
import com.hnpper.cocktailapp.model.Cocktail
import com.hnpper.cocktailapp.databinding.ListItemCardBinding
import com.hnpper.cocktailapp.utilities.CocktailComparator
import kotlinx.android.synthetic.main.list_item_card.view.*
import java.text.SimpleDateFormat

class HomeAdapter : ListAdapter<Cocktail, HomeAdapter.CocktailViewHolder>(CocktailComparator) {

    var listener: Listener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_card, parent, false)
        return CocktailViewHolder(view!!)
    }

    override fun onBindViewHolder(holder: CocktailViewHolder, position: Int) {
        val cocktail = getItem(position)
        holder.cocktail = cocktail

        //holder.ivCocktail? PICASSO!!!
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
