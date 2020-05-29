package com.hnpper.cocktailapp.ui.search

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.dagger.getViewModelFromFactory
import co.zsmb.rainbowcake.navigation.navigator
import com.google.gson.GsonBuilder
import com.hnpper.cocktailapp.R
import com.hnpper.cocktailapp.model.Cocktail
import com.hnpper.cocktailapp.model.ResponseList
import com.hnpper.cocktailapp.model.User
import com.hnpper.cocktailapp.remote.RemoteServiceInterface
import com.hnpper.cocktailapp.ui.detail.DetailFragment
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchFragment : RainbowCakeFragment<SearchViewState, SearchViewModel>(),
SearchAdapter.Listener {
    override fun provideViewModel() = getViewModelFromFactory()
    override fun getViewResource() = R.layout.fragment_search

    private val searchAdapter = SearchAdapter()
    private lateinit var sharedPref: SharedPreferences
    private lateinit var currentUser: User
    private lateinit var cocktailList: MutableList<Cocktail>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        cocktailList= mutableListOf()
        //addDummyCocktail()
        initAdapter()
    }

    override fun onStart() {
        super.onStart()
        viewModel.loadData()
    }

    private fun initAdapter() {
        rvSearch.adapter = searchAdapter
        rvSearch.layoutManager = LinearLayoutManager(requireContext()) as RecyclerView.LayoutManager
        searchAdapter.listener = this
        searchAdapter.submitList(cocktailList)
    }

    override fun render(viewState: SearchViewState) {
        when (viewState) {
            is Loading -> {
                progressBar.visibility = View.VISIBLE
            }
            is Loaded -> {
                progressBar.visibility = View.GONE
                btnSearch.setOnClickListener {
                    val searchName = etSearchName.text.toString()
                    viewModel.loadSearch(searchName)
                }
            }
            is GetSearchResult -> {
                progressBar.visibility = View.VISIBLE
            }
            is SearchLoaded -> {
                progressBar.visibility = View.GONE
                for (cocktail in viewModel.cocktailList) {
                    cocktailList.add(cocktail)
                    searchAdapter.notifyItemChanged(cocktailList.size - 1)
                }
            }
        }
    }

    override fun onCocktailClicked(cocktail: Cocktail) {
        val bundle = bundleOf("cocktailId" to cocktail.idDrink)
        findNavController().navigate(R.id.detailFragment, bundle)
    }

    private fun addDummyCocktail() {
        cocktailList.add(Cocktail(1,"Dummy","jo", "alcoholic", "uveg", "Razd ossze", "so", "bors", "citrom", "","","","","","","","","","","","","https://cdn.diffords.com/contrib/stock-images/2017/1/37/20177e6f84ec35b29061cd85c72d3d1011c5.jpg" ))
    }


}