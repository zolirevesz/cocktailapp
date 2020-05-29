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
import com.hnpper.cocktailapp.model.Cocktail
import com.hnpper.cocktailapp.model.User
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail.*
import javax.inject.Inject

class DetailFragment : RainbowCakeFragment<DetailViewState, DetailViewModel>() {
    override fun provideViewModel() = getViewModelFromFactory()
    override fun getViewResource() = R.layout.fragment_detail

    private lateinit var cocktail: Cocktail

    private lateinit var currentUser : User

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cocktail = viewModel.getCocktail(arguments?.getInt("cocktailId") as Int)
        //cocktail = getDummy()
    }

    override fun render(viewState: DetailViewState) {
        when (viewState) {
            is Loading -> {
                progressBar.visibility = View.VISIBLE
                viewModel.load()
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
                    fabAdd.visibility = View.GONE
                    fabRemove.visibility = View.VISIBLE
                } else {
                    fabAdd.visibility = View.VISIBLE
                    fabRemove.visibility = View.GONE
                }

                fabAdd.setOnClickListener{
                    viewState.user.favCocktailsId?.add(cocktail.idDrink)
                    viewModel.saveUser(viewState.user, viewState.user.password, Uri.parse(viewState.user.photoImageUrl))
                    fabAdd.visibility = View.GONE
                    fabRemove.visibility = View.VISIBLE
                }

                fabRemove.setOnClickListener{
                    viewState.user.favCocktailsId!!.removeAt(viewState.user.favCocktailsId!!.indexOf(cocktail.idDrink)+1) // miért -1 az index ha a méret 1, és annak az egy elemnek az indexét keresem?
                    viewModel.saveUser(viewState.user, viewState.user.password, Uri.parse(viewState.user.photoImageUrl))
                    fabAdd.visibility = View.VISIBLE
                    fabRemove.visibility = View.GONE
                }
            }
        }
    }

    private fun createIngredientList(): String {
        var ingredientString: String = ""

        if (!cocktail.strIngredient1.isNullOrEmpty()) {
            ingredientString += cocktail.strIngredient1
        }
        if (!cocktail.strIngredient2.isNullOrEmpty()) {
            ingredientString += ", " + cocktail.strIngredient2
        }
        if (!cocktail.strIngredient3.isNullOrEmpty()) {
            ingredientString += ", " + cocktail.strIngredient3
        }
        if (!cocktail.strIngredient4.isNullOrEmpty()) {
            ingredientString += ", " + cocktail.strIngredient4
        }
        if (!cocktail.strIngredient5.isNullOrEmpty()) {
            ingredientString += ", " + cocktail.strIngredient5
        }
        if (!cocktail.strIngredient6.isNullOrEmpty()) {
            ingredientString += ", " + cocktail.strIngredient6
        }
        if (!cocktail.strIngredient7.isNullOrEmpty()) {
            ingredientString += ", " + cocktail.strIngredient7
        }
        if (!cocktail.strIngredient8.isNullOrEmpty()) {
            ingredientString += ", " + cocktail.strIngredient8
        }
        if (!cocktail.strIngredient9.isNullOrEmpty()) {
            ingredientString += ", " + cocktail.strIngredient9
        }
        if (!cocktail.strIngredient10.isNullOrEmpty()) {
            ingredientString += ", " + cocktail.strIngredient10
        }
        if (!cocktail.strIngredient11.isNullOrEmpty()) {
            ingredientString += ", " + cocktail.strIngredient11
        }
        if (!cocktail.strIngredient12.isNullOrEmpty()) {
            ingredientString += ", " + cocktail.strIngredient12
        }
        if (!cocktail.strIngredient13.isNullOrEmpty()) {
            ingredientString += ", " + cocktail.strIngredient13
        }
        if (!cocktail.strIngredient14.isNullOrEmpty()) {
            ingredientString += ", " + cocktail.strIngredient14
        }
        if (!cocktail.strIngredient15.isNullOrEmpty()) {
            ingredientString += ", " + cocktail.strIngredient15
        }

        return ingredientString
    }

    private fun isCocktailAdded(): Boolean {
        for (cocktailID in currentUser.favCocktailsId!!) {
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


    private fun getDummy() : Cocktail {
        return Cocktail(1,"Dummy","jo", "alcoholic", "uveg", "Razd ossze", "so", "bors", "citrom", "","","","","","","","","","","","","https://cdn.diffords.com/contrib/stock-images/2017/1/37/20177e6f84ec35b29061cd85c72d3d1011c5.jpg" )
    }

}