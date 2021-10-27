package com.nrchsnl.technicaltest.ui.search

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.nrchsnl.technicaltest.MainEnvironment
import com.nrchsnl.technicaltest.R
import com.nrchsnl.technicaltest.base.BaseFragment
import com.nrchsnl.technicaltest.data.remote.model.Datas
import com.nrchsnl.technicaltest.data.remote.model.genre.Genre
import com.nrchsnl.technicaltest.di.DaggerMainComponent
import com.nrchsnl.technicaltest.di.MainDataModule
import com.nrchsnl.technicaltest.helper.extensions.setupGridLayoutManager
import com.nrchsnl.technicaltest.ui.genre.MovieAdapter
import com.nrchsnl.technicaltest.ui.home.GenreAdapter
import com.nrchsnl.technicaltest.ui.home.HomeListener
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Inject

class SearchFragment: BaseFragment<SearchViewModel>(), HomeListener {
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var genreAdapter : GenreAdapter

    companion object {
        fun newInstance(bundle: Bundle): SearchFragment {
            return SearchFragment().apply {
                arguments = bundle
            }
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val mViewModel: SearchViewModel by lazy {
        ViewModelProviders
            .of(this, viewModelFactory)[SearchViewModel::class.java]
    }

    override fun getLayoutRes(): Int = R.layout.fragment_search

    override fun onCreateInjector() {
        DaggerMainComponent.builder()
            .mainDataModule(MainDataModule(requireContext()))
            .build()
            .inject(this)
    }

    override fun onCreateViewModel(): SearchViewModel = mViewModel
    override fun onCreateObserver(viewModel: SearchViewModel) {
        viewModel.apply {
            movie.observe(this@SearchFragment, Observer{
                setupMovie(it)
            })
        }
    }

    override fun setContentData() {
        etSearch.setOnKeyListener(object : View.OnKeyListener{
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
                if(event?.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER){
                    mViewModel.getListMovie(etSearch.text.toString())
                    return true
                }
                return false
            }
        })

    }

    override fun onItemClick(data: Datas) {
        val bundle = Bundle()
        bundle.putString("id", data.id.toString())
        MainEnvironment.routeNavigation.openDetailPage(requireContext(), bundle)
    }

    override fun onGenreClick(data: Genre) {}

    private fun setupMovie(datas: List<Datas>) {
        if(datas.isNullOrEmpty()){
            tvNoList.visibility = View.VISIBLE
        }else {
            tvNoList.visibility = View.GONE
            movieAdapter = MovieAdapter(this)
            rvItem.apply {
                setHasFixedSize(true)
                setupGridLayoutManager(3)
                adapter = movieAdapter
            }
            movieAdapter.setRecyclerData(datas)
        }
    }
}