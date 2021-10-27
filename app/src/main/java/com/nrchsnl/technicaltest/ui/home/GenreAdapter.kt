package com.nrchsnl.technicaltest.ui.home

import android.view.View
import com.nrchsnl.technicaltest.R
import com.nrchsnl.technicaltest.base.BaseAdapter
import com.nrchsnl.technicaltest.data.remote.model.genre.Genre
import kotlinx.android.synthetic.main.item_list_genre.view.*


class GenreAdapter(private val mListener: HomeListener) : BaseAdapter<Genre>() {
    override val layout: Int = R.layout.item_list_genre

    override fun bind(data: Genre, itemView: View, position: Int) {
        itemView.apply {
            tvGenre.setText(data.name)
            itemView.setOnClickListener {
                mListener.onGenreClick(data)
            }
        }
    }
}