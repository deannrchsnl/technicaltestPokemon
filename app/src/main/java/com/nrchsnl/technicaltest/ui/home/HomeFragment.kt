package com.nrchsnl.technicaltest.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.nrchsnl.technicaltest.MainEnvironment.routeNavigation.openDetailPage
import com.nrchsnl.technicaltest.MainEnvironment.routeNavigation.openGenreMoviePage
import com.nrchsnl.technicaltest.MainEnvironment.routeNavigation.openSearchMoviePage
import com.nrchsnl.technicaltest.R
import com.nrchsnl.technicaltest.base.BaseFragment
import com.nrchsnl.technicaltest.data.remote.model.Datas
import com.nrchsnl.technicaltest.data.remote.model.genre.Genre
import com.nrchsnl.technicaltest.di.DaggerMainComponent
import com.nrchsnl.technicaltest.di.MainDataModule
import com.nrchsnl.technicaltest.helper.extensions.getBaseUrlImage
import com.nrchsnl.technicaltest.helper.extensions.setImageUrl
import com.nrchsnl.technicaltest.helper.extensions.setupLinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.item_film_1.view.*
import javax.inject.Inject

class HomeFragment : BaseFragment<HomeViewModel>(), HomeListener {

    private lateinit var nowShowingAdapter : HomeAdapter1
    private lateinit var topRatedAdapter : HomeAdapter2
    private lateinit var popularAdapter : HomeAdapter2
    private lateinit var upcomingAdapter : HomeAdapter2
    private lateinit var genreAdapter : GenreAdapter

    companion object {
        fun newInstance(bundle: Bundle): HomeFragment {
            return HomeFragment().apply {
                arguments = bundle
            }
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val mViewModel: HomeViewModel by lazy {
        ViewModelProviders
            .of(this, viewModelFactory)[HomeViewModel::class.java]
    }

    override fun getLayoutRes(): Int = R.layout.fragment_home

    override fun onCreateInjector() {
        DaggerMainComponent.builder()
            .mainDataModule(MainDataModule(requireContext()))
            .build()
            .inject(this)
    }

    override fun onCreateViewModel(): HomeViewModel = mViewModel

    override fun onCreateObserver(viewModel: HomeViewModel) {
        viewModel.apply {
            nowPlaying.observe(this@HomeFragment, Observer {
                setupNowShowing(it)
            })

            topRated.observe(this@HomeFragment, Observer {
                setupTopRated(it)
            })
            popular.observe(this@HomeFragment, Observer {
                setupPopular(it)
            })
            upComing.observe(this@HomeFragment, Observer {
                setupUpcoming(it)
            })
            trending.observe(this@HomeFragment, Observer { data ->
                tvTrending.setText(data.title)
                data.backdropPath?.let { ivTrending.setImageUrl(getBaseUrlImage(it)) }
                lyTrending.setOnClickListener { onItemClick(data) }
            })
            genre.observe(this@HomeFragment, Observer {
                setupGenre(it)
            })
        }
    }

    override fun setContentData() {
        mViewModel.getListNowPlaying()
        mViewModel.getListTopRated()
        mViewModel.getListPopular()
        mViewModel.getListUpcoming()
        mViewModel.getListTrending()
        mViewModel.getListGenre()

        lyGenre.visibility = View.GONE

        tvKategori.setOnClickListener {
            lyGenre.visibility = View.VISIBLE
        }
        tvClose.setOnClickListener {
            lyGenre.visibility = View.GONE
        }

        search.setOnClickListener { openSearchMoviePage(requireContext()) }
    }

    private fun setupNowShowing(datas: List<Datas>) {
        nowShowingAdapter = HomeAdapter1(this)
        rvNowShowing.apply {
            setHasFixedSize(true)
            setupLinearLayoutManager(RecyclerView.HORIZONTAL, false)
            adapter = nowShowingAdapter
        }
        nowShowingAdapter.setRecyclerData(datas ?: listOf())
    }

    private fun setupTopRated(datas: List<Datas>) {
        topRatedAdapter = HomeAdapter2(this)
        rvTopRated.apply {
            setHasFixedSize(true)
            setupLinearLayoutManager(RecyclerView.HORIZONTAL, false)
            adapter = topRatedAdapter
        }
        topRatedAdapter.setRecyclerData(datas)
    }

    private fun setupPopular(datas: List<Datas>) {
        popularAdapter = HomeAdapter2(this)
        rvPopular.apply {
            setHasFixedSize(true)
            setupLinearLayoutManager(RecyclerView.HORIZONTAL, false)
            adapter = popularAdapter
        }
        popularAdapter.setRecyclerData(datas)
    }

    private fun setupUpcoming(datas: List<Datas>) {
        upcomingAdapter = HomeAdapter2(this)
        rvUpcoming.apply {
            setHasFixedSize(true)
            setupLinearLayoutManager(RecyclerView.HORIZONTAL, false)
            adapter = upcomingAdapter
        }
        upcomingAdapter.setRecyclerData(datas)
    }
    private fun setupGenre(datas: List<Genre>) {
        genreAdapter = GenreAdapter(this)
        rvGenre.apply {
            setHasFixedSize(true)
            setupLinearLayoutManager(RecyclerView.VERTICAL, false)
            adapter = genreAdapter
        }
        genreAdapter.setRecyclerData(datas)
    }

    override fun onItemClick(data: Datas) {
        val bundle = Bundle()
        bundle.putString("id", data.id.toString())
        openDetailPage(requireContext(), bundle)
    }

    override fun onGenreClick(data: Genre) {
        val bundle = Bundle()
        bundle.putString("idGenre", data.id.toString())
        bundle.putString("namaGenre", data.name)
        openGenreMoviePage(requireContext(), bundle)
    }
}