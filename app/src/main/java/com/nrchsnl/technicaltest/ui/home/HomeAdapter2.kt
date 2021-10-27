package com.nrchsnl.technicaltest.ui.home

import android.view.View
import com.nrchsnl.technicaltest.R
import com.nrchsnl.technicaltest.base.BaseAdapter
import com.nrchsnl.technicaltest.data.remote.model.Datas
import com.nrchsnl.technicaltest.helper.extensions.getBaseUrlImage
import com.nrchsnl.technicaltest.helper.extensions.setImageUrl
import kotlinx.android.synthetic.main.item_film_1.view.*

class HomeAdapter2(private val mListener: HomeListener) : BaseAdapter<Datas>() {
    override val layout: Int = R.layout.item_film_2

    override fun bind(data: Datas, itemView: View, position: Int) {
        itemView.apply {
            title.setText(data.title)
            if(!data.backdropPath.isNullOrEmpty()) {
                data.backdropPath?.let { imgPoster.setImageUrl(getBaseUrlImage(it)) }
            }
        }
        itemView.setOnClickListener {
            mListener.onItemClick(data)
        }
    }
}