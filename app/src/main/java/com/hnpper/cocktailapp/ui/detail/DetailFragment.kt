package com.hnpper.cocktailapp.ui.detail

import android.os.Bundle
import android.revesz.seriestracker_v2.utilities.InjectorUtils
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

class DetailFragment : Fragment() {

    private val args: DetailFragmentArgs by navArgs()

    private val detailViewModel: DetailViewModel by viewModels {
        InjectorUtils.provideDetailViewModelFactory(requireActivity(), args.idDrink)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentDetailBinding>(
            inflater, R.layout.fragment_detail, container, false
        ).apply {
            Picasso.with(context).load(detailViewModel.getImgUrl()).into(imgCocktail)
            viewModel = detailViewModel
            lifecycleOwner = viewLifecycleOwner
            callback = object : Callback {
                override fun add(cocktail: Cocktail?) {
                    cocktail?.let {
                        hideAppBarFab(fab)
                        detailViewModel.update(it)
                        Snackbar.make(root, "Cocktail added!", Snackbar.LENGTH_LONG)
                            .show()
                    }
                }
            }
        }


        return binding.root
    }

    private fun hideAppBarFab(fab: FloatingActionButton) {
        val params = fab.layoutParams as CoordinatorLayout.LayoutParams
        val behavior = params.behavior as FloatingActionButton.Behavior
        behavior.isAutoHideEnabled = false
        fab.hide()
    }


    interface Callback {
        fun add(cocktail: Cocktail?)
    }

}