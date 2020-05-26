package com.hnpper.cocktailapp.ui.detail

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.dagger.getViewModelFromFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.hnpper.cocktailapp.R
import com.hnpper.cocktailapp.databinding.FragmentDetailBinding
import com.hnpper.cocktailapp.model.Cocktail
import com.hnpper.cocktailapp.model.User
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail.*
import javax.inject.Inject

class DetailFragment @Inject constructor(
    private val cocktail : Cocktail
): RainbowCakeFragment<DetailViewState, DetailViewModel>() {
    override fun provideViewModel() = getViewModelFromFactory()
    override fun getViewResource() = R.layout.fragment_detail

    private lateinit var currentUser : User

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun render(viewState: DetailViewState) {
        when (viewState) {
            is Loading -> {
                progressBar.visibility = View.VISIBLE
            }
            is Loaded -> {
                progressBar.visibility = View.GONE

                currentUser = viewState.user

                Picasso.get().load(cocktail.strDrinkThumb).into(imgCocktail)
                tvCocktailName.setText(cocktail.strDrink)
                tvCategory.setText(cocktail.strCategory)
                tvGlass.setText(cocktail.strGlass)
                tvIngredients.setText(createIngredientList())
                tvInstruction.setText(cocktail.strInstruction)

                if(isCocktailAdded()) {
                    fab.visibility = View.GONE
                } else {
                    fab.visibility = View.VISIBLE
                }

                fab.setOnClickListener{
                    viewState.user.favCocktailsId.add(cocktail.idDrink)
                    viewModel.saveUser(viewState.user, viewState.user.password, Uri.parse(viewState.user.photoImageUrl))
                }
            }
        }
    }

    private fun createIngredientList(): String {
        var ingredientString: String = ""

        if (cocktail.strIngredient1 != null) {
            ingredientString.plus(cocktail.strIngredient1)
        }
        if (cocktail.strIngredient2 != null) {
            ingredientString.plus(", " + cocktail.strIngredient2)
        }
        if (cocktail.strIngredient3 != null) {
            ingredientString.plus(", " + cocktail.strIngredient3)
        }
        if (cocktail.strIngredient4 != null) {
            ingredientString.plus(", " + cocktail.strIngredient4)
        }
        if (cocktail.strIngredient5 != null) {
            ingredientString.plus(", " + cocktail.strIngredient5)
        }
        if (cocktail.strIngredient6 != null) {
            ingredientString.plus(", " + cocktail.strIngredient6)
        }
        if (cocktail.strIngredient7 != null) {
            ingredientString.plus(", " + cocktail.strIngredient7)
        }
        if (cocktail.strIngredient8 != null) {
            ingredientString.plus(", " + cocktail.strIngredient8)
        }
        if (cocktail.strIngredient9 != null) {
            ingredientString.plus(", " + cocktail.strIngredient9)
        }
        if (cocktail.strIngredient10 != null) {
            ingredientString.plus(", " + cocktail.strIngredient10)
        }
        if (cocktail.strIngredient11 != null) {
            ingredientString.plus(", " + cocktail.strIngredient11)
        }
        if (cocktail.strIngredient12 != null) {
            ingredientString.plus(", " + cocktail.strIngredient12)
        }
        if (cocktail.strIngredient13 != null) {
            ingredientString.plus(", " + cocktail.strIngredient13)
        }
        if (cocktail.strIngredient14 != null) {
            ingredientString.plus(", " + cocktail.strIngredient14)
        }
        if (cocktail.strIngredient15 != null) {
            ingredientString.plus(", " + cocktail.strIngredient15)
        }

        return ingredientString
    }

    private fun isCocktailAdded(): Boolean {
        for (cocktailID in currentUser.favCocktailsId) {
            if (cocktailID == cocktail.idDrink)
                return true
        }
        return false
    }

    private fun hideAppBarFab(fab: FloatingActionButton) {
        val params = fab.layoutParams as CoordinatorLayout.LayoutParams
        val behavior = params.behavior as FloatingActionButton.Behavior
        behavior.isAutoHideEnabled = false
        fab.hide()
    }


}