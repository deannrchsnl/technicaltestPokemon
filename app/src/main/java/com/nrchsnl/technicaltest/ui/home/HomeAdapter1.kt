package com.nrchsnl.technicaltest.ui.home

import android.opengl.Visibility
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.nrchsnl.technicaltest.R
import com.nrchsnl.technicaltest.base.BaseAdapter
import com.nrchsnl.technicaltest.data.remote.model.Datas
import com.nrchsnl.technicaltest.helper.extensions.getBaseUrlImage
import com.nrchsnl.technicaltest.helper.extensions.setImageUrl
import kotlinx.android.synthetic.main.item_film_1.view.*

class HomeAdapter1(private val mListener: HomeListener) : BaseAdapter<Datas>() {
    override val layout: Int = R.layout.item_film_1

    override fun bind(data: Datas, itemView: View, position: Int) {
        itemView.apply {
            title.setText(data.title)
            if(!data.posterPath.isNullOrEmpty()) {
                vItem.visibility = View.GONE
                title.visibility = View.GONE
                data.posterPath?.let { imgPoster.setImageUrl(getBaseUrlImage(it)) }
            }else{
                vItem.visibility = View.VISIBLE
                title.visibility = View.VISIBLE
            }
            itemView.setOnClickListener {
                mListener.onItemClick(data)
            }
        }
    }
}