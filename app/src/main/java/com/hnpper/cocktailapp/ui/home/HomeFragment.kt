package com.hnpper.cocktailapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.dagger.getViewModelFromFactory
import co.zsmb.rainbowcake.extensions.exhaustive
import com.hnpper.cocktailapp.R

class HomeFragment : RainbowCakeFragment<HomeViewState, HomeViewModel>() {
    override fun provideViewModel() = getViewModelFromFactory()
    override fun getViewResource() = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO Setup views
    }

    override fun onStart() {
        super.onStart()
        viewModel.load()
    }

    override fun render(viewState: HomeViewState) {
        // TODO Render state
        when (viewState) {
            is Loading -> {

            }
            is HomeLoaded -> {

            }
        }.exhaustive
    }
}