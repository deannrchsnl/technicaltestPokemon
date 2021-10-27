package com.nrchsnl.technicaltest.helper.extensions

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.setupLinearLayoutManager(orientation: Int, isReversed: Boolean) {
    layoutManager = LinearLayoutManager(this.context, orientation, isReversed)
    setHasFixedSize(true)
    itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
    setItemViewCacheSize(30)
    isDrawingCacheEnabled = true
    drawingCacheQuality = View.DRAWING_CACHE_QUALITY_HIGH
}

fun RecyclerView.setupGridLayoutManager(span: Int) {
    layoutManager = androidx.recyclerview.widget.GridLayoutManager(context, span)
    setHasFixedSize(true)
    itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
    setItemViewCacheSize(30)
    isDrawingCacheEnabled = true
    drawingCacheQuality = View.DRAWING_CACHE_QUALITY_HIGH
}