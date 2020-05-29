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

    private lateinit var currentUser : User

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCocktail(arguments?.getInt("cocktailId") as Int)
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

                Picasso.get().load(viewModel.cocktail.strDrinkThumb).into(imgCocktail)
                tvCocktailName.setText(viewModel.cocktail.strDrink)
                tvCategory.setText(viewModel.cocktail.strCategory)
                tvGlass.setText(viewModel.cocktail.strGlass)
                tvIngredients.setText(createIngredientList())
                tvInstruction.setText(viewModel.cocktail.strInstructions)

                if(isCocktailAdded()) {
                    fabAdd.visibility = View.GONE
                    fabRemove.visibility = View.VISIBLE
                } else {
                    fabAdd.visibility = View.VISIBLE
                    fabRemove.visibility = View.GONE
                }

                fabAdd.setOnClickListener{
                    viewState.user.favCocktailsId?.add(viewModel.cocktail.idDrink)
                    viewModel.saveUser(viewState.user, viewState.user.password, Uri.parse(viewState.user.photoImageUrl))
                    fabAdd.visibility = View.GONE
                    fabRemove.visibility = View.VISIBLE
                }

                fabRemove.setOnClickListener{
                    viewState.user.favCocktailsId!!.removeAt(viewState.user.favCocktailsId!!.indexOf(viewModel.cocktail.idDrink)+1) // miért -1 az index ha a méret 1, és annak az egy elemnek az indexét keresem?
                    viewModel.saveUser(viewState.user, viewState.user.password, Uri.parse(viewState.user.photoImageUrl))
                    fabAdd.visibility = View.VISIBLE
                    fabRemove.visibility = View.GONE
                }
            }
        }
    }

    private fun createIngredientList(): String {
        var ingredientString: String = ""

        if (!viewModel.cocktail.strIngredient1.isNullOrEmpty()) {
            ingredientString += viewModel.cocktail.strIngredient1
        }
        if (!viewModel.cocktail.strIngredient2.isNullOrEmpty()) {
            ingredientString += ", " + viewModel.cocktail.strIngredient2
        }
        if (!viewModel.cocktail.strIngredient3.isNullOrEmpty()) {
            ingredientString += ", " + viewModel.cocktail.strIngredient3
        }
        if (!viewModel.cocktail.strIngredient4.isNullOrEmpty()) {
            ingredientString += ", " + viewModel.cocktail.strIngredient4
        }
        if (!viewModel.cocktail.strIngredient5.isNullOrEmpty()) {
            ingredientString += ", " + viewModel.cocktail.strIngredient5
        }
        if (!viewModel.cocktail.strIngredient6.isNullOrEmpty()) {
            ingredientString += ", " + viewModel.cocktail.strIngredient6
        }
        if (!viewModel.cocktail.strIngredient7.isNullOrEmpty()) {
            ingredientString += ", " + viewModel.cocktail.strIngredient7
        }
        if (!viewModel.cocktail.strIngredient8.isNullOrEmpty()) {
            ingredientString += ", " + viewModel.cocktail.strIngredient8
        }
        if (!viewModel.cocktail.strIngredient9.isNullOrEmpty()) {
            ingredientString += ", " + viewModel.cocktail.strIngredient9
        }
        if (!viewModel.cocktail.strIngredient10.isNullOrEmpty()) {
            ingredientString += ", " + viewModel.cocktail.strIngredient10
        }
        if (!viewModel.cocktail.strIngredient11.isNullOrEmpty()) {
            ingredientString += ", " + viewModel.cocktail.strIngredient11
        }
        if (!viewModel.cocktail.strIngredient12.isNullOrEmpty()) {
            ingredientString += ", " + viewModel.cocktail.strIngredient12
        }
        if (!viewModel.cocktail.strIngredient13.isNullOrEmpty()) {
            ingredientString += ", " + viewModel.cocktail.strIngredient13
        }
        if (!viewModel.cocktail.strIngredient14.isNullOrEmpty()) {
            ingredientString += ", " + viewModel.cocktail.strIngredient14
        }
        if (!viewModel.cocktail.strIngredient15.isNullOrEmpty()) {
            ingredientString += ", " + viewModel.cocktail.strIngredient15
        }

        return ingredientString
    }

    private fun isCocktailAdded(): Boolean {
        for (cocktailID in currentUser.favCocktailsId!!) {
            if (cocktailID == viewModel.cocktail.idDrink)
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