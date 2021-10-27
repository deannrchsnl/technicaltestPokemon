package com.nrchsnl.technicaltest.ui.search

import androidx.lifecycle.viewModelScope
import com.nrchsnl.technicaltest.MainUseCase
import com.nrchsnl.technicaltest.base.BaseViewModel
import com.nrchsnl.technicaltest.data.remote.model.Datas
import com.nrchsnl.technicaltest.helper.SingleLiveEvent
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchViewModel @Inject constructor() : BaseViewModel() {

    val movie = SingleLiveEvent<List<Datas>>()

    @Inject
    lateinit var mUseCase: MainUseCase

    fun getListMovie(query : String) {
        viewModelScope.launch {
            withContext(dispatcher) {
                val content = mUseCase.searchMovie(query)
                if (content.success!!) {
                    movie.postValue(content.datas)
                }
            }
        }
    }
}