package android.revesz.seriestracker_v2.utilities

import android.content.Context
import android.revesz.seriestracker_v2.data.AppDatabase
import android.revesz.seriestracker_v2.data.SeriesRepository
import android.revesz.seriestracker_v2.ui.allseries.AllSeriesViewModelFactory
import android.revesz.seriestracker_v2.ui.home.HomeViewModelFactory
import android.revesz.seriestracker_v2.viewmodels.ShowDetailsViewModelFactory
import com.hnpper.cocktailapp.data.disk.CocktailRepository
import com.hnpper.cocktailapp.data.disk.CocktailRoomDatabase
import com.hnpper.cocktailapp.ui.detail.DetailViewModelFactory
import com.hnpper.cocktailapp.ui.home.HomeViewModelFactory

object InjectorUtils {
    private fun getCocktailRepository(context: Context): CocktailRepository {
        return CocktailRepository.getInstance(
            CocktailRoomDatabase.getInstance(context.applicationContext).CocktailDao())
    }

    fun provideHomeViewModelFactory(context: Context): HomeViewModelFactory {
        val repository = getCocktailRepository(context)
        return HomeViewModelFactory(repository)
    }

    fun provideDetailViewModelFactory(context: Context): SearchViewModelFactory {
        val repository = getCocktailRepository(context)
        return SearchViewModelFactory(repository)
    }

    fun provideDetailViewModelFactory(
        context: Context,
        showId: Int
    ): DetailViewModelFactory {
        return DetailViewModelFactory(getCocktailRepository(context),
            showId)
    }
}