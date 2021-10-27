package com.nrchsnl.technicaltest.ui.detail

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.nrchsnl.technicaltest.R
import com.nrchsnl.technicaltest.base.BaseFragment
import com.nrchsnl.technicaltest.data.remote.model.detail.ReturnDetail
import com.nrchsnl.technicaltest.data.remote.model.genre.Genre
import com.nrchsnl.technicaltest.data.remote.model.review.Rating
import com.nrchsnl.technicaltest.di.DaggerMainComponent
import com.nrchsnl.technicaltest.di.MainDataModule
import com.nrchsnl.technicaltest.helper.extensions.changeFormatDate
import com.nrchsnl.technicaltest.helper.extensions.getBaseUrlImage
import com.nrchsnl.technicaltest.helper.extensions.setImageUrl
import com.nrchsnl.technicaltest.helper.extensions.setupLinearLayoutManager
import com.nrchsnl.technicaltest.ui.home.GenreAdapter
import com.nrchsnl.technicaltest.ui.home.HomeAdapter2
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.item_film_1.view.*
import javax.inject.Inject


class DetailFragment : BaseFragment<DetailViewModel>(), DetailListener {

    private lateinit var ratingAdapter : RatingAdapter
    companion object {
        fun newInstance(bundle: Bundle): DetailFragment {
            return DetailFragment().apply {
                arguments = bundle
            }
        }
    }

    private var idMovie = ""
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val mViewModel: DetailViewModel by lazy {
        ViewModelProviders
            .of(this, viewModelFactory)[DetailViewModel::class.java]
    }

    override fun getLayoutRes(): Int = R.layout.fragment_detail

    override fun onCreateInjector() {
        DaggerMainComponent.builder()
            .mainDataModule(MainDataModule(requireContext()))
            .build()
            .inject(this)
    }

    override fun onCreateViewModel(): DetailViewModel = mViewModel

    override fun onCreateObserver(viewModel: DetailViewModel) {
        viewModel.apply {
            data.observe(this@DetailFragment, Observer {
                setDetail(it)
            })
            linkVideo.observe(this@DetailFragment, Observer {
                setVideo(it)
            })
            haveVideo.observe(this@DetailFragment, Observer {
                ivPoster.visibility = View.VISIBLE
            })
            ratingList.observe(this@DetailFragment, Observer {
                setRating(it)
            })

        }
    }

    override fun setContentData() {
        if ((requireActivity() as DetailActivity).intent.hasExtra("id")) {
            idMovie = (requireActivity() as DetailActivity).intent.getStringExtra("id") ?: ""
            mViewModel.getDetail(idMovie)
            mViewModel.getVideo(idMovie)
            mViewModel.getRating(idMovie)
        }

        ivBack.setOnClickListener {
            (requireActivity() as DetailActivity).finish()
        }
    }

    fun setDetail(data : ReturnDetail){
        tvTitle.setText(data.title)
        var genre = ""
        for(genres in data.genres!!){
            genre += genres.name + " - "
        }
        tvGenre.setText(genre)
        tvRating.setText(data.voteAverage.toString())
        tvdate.setText(changeFormatDate(data.releaseDate!!))
        tvContent.setText(data.overview)
        data.backdropPath?.let { ivPoster.setImageUrl(getBaseUrlImage(it)) }
    }

    fun setVideo(link : String){
        lifecycle.addObserver(video)

        video.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(link, 0f)
            }
        })

    }
    fun setRating(datas: List<Rating>){

        if(!datas.isNullOrEmpty()) {
            tvNorating.visibility = View.GONE
            ratingAdapter = RatingAdapter(this)
            rvRating.apply {
                setHasFixedSize(true)
                setupLinearLayoutManager(RecyclerView.VERTICAL, false)
                adapter = ratingAdapter
            }
            ratingAdapter.setRecyclerData(datas)
        }
    }

}