package com.hnpper.cocktailapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hnpper.cocktailapp.model.Cocktail
import com.hnpper.cocktailapp.databinding.ListItemCardBinding

class HomeAdapter : ListAdapter<Cocktail, RecyclerView.ViewHolder>(CocktailDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CocktailViewHolder(ListItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val cocktail = getItem(position)
        (holder as CocktailViewHolder).bind(cocktail)
    }

    class CocktailViewHolder(
        private val binding: ListItemCardBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.cocktail?.let { item ->
                    navigateToCocktail(item, it)
                }
            }
        }

        private fun navigateToCocktail(
            cocktail: Cocktail,
            it: View
        ) {
            val direction =
                HomeFragmentDirections.actionNavAllseriesToDetailFragment(
                    cocktail.idDrink
                )
            it.findNavController().navigate(direction)
        }

        fun bind(item: Cocktail) {
            binding.apply {
                cocktail = item
                executePendingBindings()
            }
        }
    }
}

private class CocktailDiffCallback : DiffUtil.ItemCallback<Cocktail>() {

    override fun areItemsTheSame(oldItem: Cocktail, newItem: Cocktail): Boolean {
        return oldItem.idDrink == newItem.idDrink
    }

    override fun areContentsTheSame(oldItem: Cocktail, newItem: Cocktail): Boolean {
        return oldItem == newItem
    }
}