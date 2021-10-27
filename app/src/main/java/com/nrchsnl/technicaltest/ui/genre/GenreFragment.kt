package com.nrchsnl.technicaltest.ui.genre

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.nrchsnl.technicaltest.MainEnvironment
import com.nrchsnl.technicaltest.R
import com.nrchsnl.technicaltest.base.BaseFragment
import com.nrchsnl.technicaltest.data.remote.model.Datas
import com.nrchsnl.technicaltest.data.remote.model.genre.Genre
import com.nrchsnl.technicaltest.di.DaggerMainComponent
import com.nrchsnl.technicaltest.di.MainDataModule
import com.nrchsnl.technicaltest.helper.extensions.setupGridLayoutManager
import com.nrchsnl.technicaltest.helper.extensions.setupLinearLayoutManager
import com.nrchsnl.technicaltest.ui.home.GenreAdapter
import com.nrchsnl.technicaltest.ui.home.HomeAdapter1
import com.nrchsnl.technicaltest.ui.home.HomeAdapter2
import com.nrchsnl.technicaltest.ui.home.HomeListener
import kotlinx.android.synthetic.main.fragment_genre.*
import kotlinx.android.synthetic.main.fragment_genre.lyGenre
import kotlinx.android.synthetic.main.fragment_genre.rvGenre
import kotlinx.android.synthetic.main.fragment_genre.tvClose
import kotlinx.android.synthetic.main.fragment_genre.tvKategori
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class GenreFragment : BaseFragment<GenreViewModel>(), HomeListener {
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var genreAdapter : GenreAdapter

    companion object {
        fun newInstance(bundle: Bundle): GenreFragment {
            return GenreFragment().apply {
                arguments = bundle
            }
        }
    }

    private var idGenre = ""
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val mViewModel: GenreViewModel by lazy {
        ViewModelProviders
            .of(this, viewModelFactory)[GenreViewModel::class.java]
    }

    override fun getLayoutRes(): Int = R.layout.fragment_genre

    override fun onCreateInjector() {
        DaggerMainComponent.builder()
            .mainDataModule(MainDataModule(requireContext()))
            .build()
            .inject(this)
    }

    override fun onCreateViewModel(): GenreViewModel = mViewModel
    override fun onCreateObserver(viewModel: GenreViewModel) {
        viewModel.apply {
            movie.observe(this@GenreFragment, Observer{
                setupMovie(it)
            })
            genre.observe(this@GenreFragment, Observer {
                setupGenre(it)
            })
        }
    }

    override fun setContentData() {
        mViewModel.getListGenre()
        if ((requireActivity() as GenreActivity).intent.hasExtra("idGenre")) {
            idGenre = (requireActivity() as GenreActivity).intent.getStringExtra("idGenre") ?: ""
            tvKategori.setText((requireActivity() as GenreActivity).intent.getStringExtra("namaGenre") ?: "")
            mViewModel.getListMovie(idGenre)
        }

        lyGenre.visibility = View.GONE

        tvKategori.setOnClickListener {
            lyGenre.visibility = View.VISIBLE
        }
        tvClose.setOnClickListener {
            lyGenre.visibility = View.GONE
        }
    }

    override fun onItemClick(data: Datas) {
        val bundle = Bundle()
        bundle.putString("id", data.id.toString())
        MainEnvironment.routeNavigation.openDetailPage(requireContext(), bundle)
    }

    override fun onGenreClick(data: Genre) {
        lyGenre.visibility = View.GONE
        tvKategori.setText(data.name)
        mViewModel.getListMovie(data.id.toString())
    }

    private fun setupMovie(datas: List<Datas>) {
        movieAdapter = MovieAdapter(this)
        rvItem.apply {
            setHasFixedSize(true)
            setupGridLayoutManager(3)
            adapter = movieAdapter
        }
        movieAdapter.setRecyclerData(datas)
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

}