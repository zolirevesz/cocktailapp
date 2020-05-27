package com.hnpper.cocktailapp.ui.home

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.dagger.getViewModelFromFactory
import co.zsmb.rainbowcake.navigation.navigator
import com.google.gson.GsonBuilder
import com.hnpper.cocktailapp.R
import com.hnpper.cocktailapp.model.Cocktail
import com.hnpper.cocktailapp.model.User
import com.hnpper.cocktailapp.remote.RemoteServiceInterface
import com.hnpper.cocktailapp.ui.detail.DetailFragment
import com.hnpper.cocktailapp.ui.login.LoginFragment
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.nav_header_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment :
    RainbowCakeFragment<HomeViewState, HomeViewModel>(),
    HomeAdapter.Listener{
    override fun provideViewModel() = getViewModelFromFactory()
    override fun getViewResource() = R.layout.fragment_home

    private val homeAdapter = HomeAdapter()
    private lateinit var sharedPref: SharedPreferences
    private lateinit var currentUser: User

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
            is HomeWithoutLogin -> {
                username?.text = "User"
                usermail?.text = "user@example.com"
                ivSignOut.visibility = View.GONE
                findNavController().navigate(R.id.nav_login)
            }
            is HomeLoaded -> {
                tvName.text = viewState.user.name
                currentUser = viewState.user
                ivSignOut.visibility = View.VISIBLE
                val cocktails: MutableList<Cocktail> = getCocktailList()
                homeAdapter.submitList(cocktails)
            }
            is LoggedOut -> {
                Toast.makeText(
                    requireContext(),
                    "Logged out of app!",
                    Toast.LENGTH_SHORT
                ).show()
                username?.text = "User"
                usermail?.text = "user@example.com"
                ivSignOut.visibility = View.GONE
                findNavController().navigate(R.id.nav_login)
            }
        }
    }

    private fun getCocktailList() : MutableList<Cocktail> {
        var list : MutableList<Cocktail> = mutableListOf()
        val webservice by lazy {
            Retrofit.Builder()
                .baseUrl("https://www.thecocktaildb.com/api/json/v1/")
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build().create(RemoteServiceInterface::class.java)
        }

        for (cocktailId in currentUser.favCocktailsId) {
            GlobalScope.launch {
                list.add(webservice.getCocktailById(cocktailId))
            }
        }

        return list
    }

    override fun onCocktailClicked(cocktail: Cocktail) {
        val bundle = bundleOf("cocktailId" to cocktail.idDrink)
        findNavController().navigate(R.id.detailFragment, bundle)
    }
}