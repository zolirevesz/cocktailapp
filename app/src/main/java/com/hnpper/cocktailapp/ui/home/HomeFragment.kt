package com.hnpper.cocktailapp.ui.home

import android.os.Bundle
import android.revesz.seriestracker_v2.utilities.InjectorUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.RecyclerView
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.dagger.getViewModelFromFactory
import co.zsmb.rainbowcake.extensions.exhaustive
import com.hnpper.cocktailapp.R
import com.hnpper.cocktailapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    /*override fun provideViewModel() = getViewModelFromFactory()
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
                val viewManager = RecyclerView.LayoutManager(this)
                val viewAdapter = HomeAdapter()
                viewAdapter.submitList(viewState.cocktailList)
                viewState.cocktailList.observe(viewLifecycleOwner) { list ->
                    viewAdapter.submitList(list)
                }
                recyclerView = findViewById<RecyclerView>(R.id.my_recycler_view).apply {
                    // use this setting to improve performance if you know that changes
                    // in content do not change the layout size of the RecyclerView
                    setHasFixedSize(true)

                    // use a linear layout manager
                    layoutManager = viewManager

                    // specify an viewAdapter (see also next example)
                    adapter = viewAdapter

                }
            }
        }.exhaustive
        */

    private val homeViewModel: HomeViewModel by viewModels {
        InjectorUtils.provideHomeViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        context ?: return binding.root
        val adapter = HomeAdapter()
        binding.cocktailList.adapter = adapter
        subscribeUi(adapter)

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun subscribeUi(adapter: HomeAdapter) {
        homeViewModel.list.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
        }
    }

}