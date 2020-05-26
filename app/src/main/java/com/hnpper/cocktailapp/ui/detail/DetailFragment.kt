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
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail.*
import javax.inject.Inject

class DetailFragment @Inject constructor(
    private val cocktail : Cocktail
): RainbowCakeFragment<DetailViewState, DetailViewModel>() {
    override fun provideViewModel() = getViewModelFromFactory()
    override fun getViewResource() = R.layout.fragment_detail

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
                fab.setOnClickListener{
                    viewState.user.favCocktailsId.add(11111) // cocktailID
                    viewModel.saveUser(viewState.user, viewState.user.password, Uri.parse(viewState.user.photoImageUrl))
                }
            }
        }
    }

    private fun hideAppBarFab(fab: FloatingActionButton) {
        val params = fab.layoutParams as CoordinatorLayout.LayoutParams
        val behavior = params.behavior as FloatingActionButton.Behavior
        behavior.isAutoHideEnabled = false
        fab.hide()
    }


}