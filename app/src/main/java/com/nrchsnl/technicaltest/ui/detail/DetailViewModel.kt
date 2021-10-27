package com.nrchsnl.technicaltest.ui.detail

import androidx.lifecycle.viewModelScope
import com.nrchsnl.technicaltest.MainUseCase
import com.nrchsnl.technicaltest.base.BaseViewModel
import com.nrchsnl.technicaltest.data.remote.model.detail.ReturnDetail
import com.nrchsnl.technicaltest.data.remote.model.genre.Genre
import com.nrchsnl.technicaltest.data.remote.model.review.Rating
import com.nrchsnl.technicaltest.helper.SingleLiveEvent
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DetailViewModel @Inject constructor() : BaseViewModel() {

    @Inject
    lateinit var mUseCase: MainUseCase

    val data = SingleLiveEvent<ReturnDetail>()
    val ratingList = SingleLiveEvent<List<Rating>>()
    val linkVideo = SingleLiveEvent<String>()
    val haveVideo = SingleLiveEvent<Boolean>()

    fun getDetail(id : String) {
        viewModelScope.launch {
            withContext(dispatcher) {
                val content = mUseCase.getDetail(id)
                if (content.success==null) {
                    data.postValue(content)
                }
            }
        }
    }

    fun getVideo(id : String) {
        viewModelScope.launch {
            withContext(dispatcher) {
                val content = mUseCase.getVideo(id)
                if (content.success!!) {
                    if(!content.datas.isNullOrEmpty()) {
                        linkVideo.postValue(content.datas?.get(content.datas.size - 1)?.key ?: "")
                    }else{
                        haveVideo.postValue(false)
                    }
                }
            }
        }
    }

    fun getRating(id : String) {
        viewModelScope.launch {
            withContext(dispatcher) {
                val content = mUseCase.getRating(id)
                if (content.success!!) {
                    ratingList.postValue(content.datas)
                }
            }
        }
    }


}