package com.nrchsnl.technicaltest.ui.detail

import android.view.View
import com.nrchsnl.technicaltest.R
import com.nrchsnl.technicaltest.base.BaseAdapter
import com.nrchsnl.technicaltest.data.remote.model.review.Rating
import com.nrchsnl.technicaltest.helper.extensions.getBaseUrlImage
import com.nrchsnl.technicaltest.helper.extensions.setImageUrl
import kotlinx.android.synthetic.main.item_rating.view.*

class RatingAdapter(private val mListener: DetailListener) : BaseAdapter<Rating>() {
    override val layout: Int = R.layout.item_rating
    override fun bind(data: Rating, itemView: View, position: Int) {
        itemView.apply {
            tvUsername.setText(data.author)
            if(!data.authorDetails?.avatarPath.isNullOrEmpty()) {
                data.authorDetails?.avatarPath?.let { avatar.setImageUrl(getBaseUrlImage(it)) }
            }
            rating.rating = (data.authorDetails?.rating)?.toFloat()?.div(2) ?: 0f
            tvUlasan.text = data.content
        }
    }
}