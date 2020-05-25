package com.hnpper.cocktailapp.ui.home

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.dagger.getViewModelFromFactory
import co.zsmb.rainbowcake.navigation.navigator
import com.hnpper.cocktailapp.R
import com.hnpper.cocktailapp.model.Cocktail
import com.hnpper.cocktailapp.ui.login.LoginFragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment :
    RainbowCakeFragment<HomeViewState, HomeViewModel>(),
    HomeAdapter.Listener{
    override fun provideViewModel() = getViewModelFromFactory()
    override fun getViewResource() = R.layout.fragment_home

    private val homeAdapter = HomeAdapter()
    private lateinit var sharedPref: SharedPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return

        initAdapter()

        ivSignOut.setOnClickListener {
            viewModel.logout()
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.loadData()
    }

    private fun initAdapter() {
        rvCocktails.adapter = homeAdapter
        rvCocktails.layoutManager = LinearLayoutManager(requireContext())
        homeAdapter.listener = this
    }

    override fun render(viewState: HomeViewState) {
        when (viewState) {
            is Loading -> {
                progressBar.visibility = View.VISIBLE
            }
            is HomeLoaded -> {
                tvName.text = viewState.user.name
                val cocktails: MutableList<Cocktail> = getCocktailList()
                homeAdapter.submitList(cocktails)
            }
            is LoggedOut -> {
                Toast.makeText(
                    requireContext(),
                    "Logged out of app!",
                    Toast.LENGTH_SHORT
                ).show()

                navigator?.run {
                    setStack(LoginFragment())
                    executePending()
                }
            }
        }
    }

    private fun getCocktailList() : MutableList<Cocktail> {
        // TODO implement
    }

    override fun onCocktailClicked(cocktail: Cocktail) {
        // TODO launch detailed view (DetailFragment)
    }
}