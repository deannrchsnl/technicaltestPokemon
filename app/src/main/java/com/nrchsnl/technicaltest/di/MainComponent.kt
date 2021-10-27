package com.nrchsnl.technicaltest.di

/**
 * Created by deannrchsnl on 25/10/21.
 */
import com.nrchsnl.technicaltest.ui.detail.DetailFragment
import com.nrchsnl.technicaltest.ui.genre.GenreFragment
import com.nrchsnl.technicaltest.ui.home.HomeFragment
import com.nrchsnl.technicaltest.ui.search.SearchFragment
import dagger.Component

@Component(modules = [
    MainDataModule::class,
    MainModule::class
])
@MainScope
interface MainComponent {
    fun inject(fragment: HomeFragment)
    fun inject(fragment: DetailFragment)
    fun inject(fragment: GenreFragment)
    fun inject(fragment: SearchFragment)
}