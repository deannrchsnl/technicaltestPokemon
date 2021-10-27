package com.nrchsnl.technicaltest.ui.home

import com.nrchsnl.technicaltest.data.remote.model.Datas
import com.nrchsnl.technicaltest.data.remote.model.genre.Genre

interface HomeListener {
    fun onItemClick(data : Datas)
    fun onGenreClick(data : Genre)
}