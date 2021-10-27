package com.nrchsnl.technicaltest.di

/**
 * Created by deannrchsnl on 25/10/21.
 */
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nrchsnl.technicaltest.base.BaseModule
import com.nrchsnl.technicaltest.base.viewmodel.ViewModelFactory
import com.nrchsnl.technicaltest.base.viewmodel.ViewModelKey
import com.nrchsnl.technicaltest.data.MainRepository
import com.nrchsnl.technicaltest.data.MainRepositoryImp
import com.nrchsnl.technicaltest.data.ServiceFactory
import com.nrchsnl.technicaltest.data.remote.MainRemoteDataSource
import com.nrchsnl.technicaltest.data.remote.MainService
import com.nrchsnl.technicaltest.ui.detail.DetailViewModel
import com.nrchsnl.technicaltest.ui.genre.GenreViewModel
import com.nrchsnl.technicaltest.ui.home.HomeViewModel
import com.nrchsnl.technicaltest.ui.search.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class MainModule{
    @MainScope
    @Binds
    internal abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @MainScope
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    internal abstract fun bindMainViewModel(viewModel: HomeViewModel): ViewModel

    @MainScope
    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    internal abstract fun bindDetailViewModel(viewModel: DetailViewModel): ViewModel

    @MainScope
    @Binds
    @IntoMap
    @ViewModelKey(GenreViewModel::class)
    internal abstract fun bindGenreViewModel(viewModel: GenreViewModel): ViewModel

    @MainScope
    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    internal abstract fun bindSearchViewModel(viewModel: SearchViewModel): ViewModel

}