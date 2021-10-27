package com.nrchsnl.technicaltest.ui.home

import android.util.Log
import com.nrchsnl.technicaltest.MainUseCase
import com.nrchsnl.technicaltest.base.BaseViewModel
import kotlinx.coroutines.withContext
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nrchsnl.technicaltest.data.remote.model.Datas
import com.nrchsnl.technicaltest.data.remote.model.genre.Genre
import com.nrchsnl.technicaltest.helper.SingleLiveEvent
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

class HomeViewModel @Inject constructor() : BaseViewModel() {

    @Inject
    lateinit var mUseCase: MainUseCase
    val nowPlaying = SingleLiveEvent<List<Datas>>()
    val upComing = SingleLiveEvent<List<Datas>>()
    val topRated = SingleLiveEvent<List<Datas>>()
    val popular = SingleLiveEvent<List<Datas>>()
    val trending = SingleLiveEvent<Datas>()
    val genre = SingleLiveEvent<List<Genre>>()

    fun getListNowPlaying() {
        viewModelScope.launch {
            withContext(dispatcher) {
                val content = mUseCase.getNowPlaying()
                if (content.success!!) {
                    nowPlaying.postValue(content.datas)
                }
            }
        }
    }

    fun getListUpcoming() {
        viewModelScope.launch {
            withContext(dispatcher) {
                val content = mUseCase.getComingsoon()
                if (content.success!!) {
                    upComing.postValue(content.datas)
                }
            }
        }
    }

    fun getListTopRated() {
        viewModelScope.launch {
            withContext(dispatcher) {
                val content = mUseCase.getTopRated()
                if (content.success!!) {
                    topRated.postValue(content.datas)
                }
            }
        }
    }

    fun getListPopular() {
        viewModelScope.launch {
            withContext(dispatcher) {
                val content = mUseCase.getPopular()
                if (content.success!!) {
                    popular.postValue(content.datas)
                }
            }
        }
    }

    fun getListTrending() {
        viewModelScope.launch {
            withContext(dispatcher) {
                val content = mUseCase.getTrending()
                if (content.success!!) {
                    val randomIndex = Random.nextInt(content.datas!!.size)
                    trending.postValue(content.datas[randomIndex])
                }
            }
        }
    }

    fun getListGenre() {
        viewModelScope.launch {
            withContext(dispatcher) {
                val content = mUseCase.getGenre()
                if (content.success!!) {
                    genre.postValue(content.genres)
                }
            }
        }
    }
}