package com.nrchsnl.technicaltest.ui.genre

import androidx.lifecycle.viewModelScope
import com.nrchsnl.technicaltest.MainUseCase
import com.nrchsnl.technicaltest.base.BaseViewModel
import com.nrchsnl.technicaltest.data.remote.model.Datas
import com.nrchsnl.technicaltest.data.remote.model.genre.Genre
import com.nrchsnl.technicaltest.helper.SingleLiveEvent
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.random.Random

class GenreViewModel @Inject constructor() : BaseViewModel() {

    val movie = SingleLiveEvent<List<Datas>>()
    val genre = SingleLiveEvent<List<Genre>>()
    @Inject
    lateinit var mUseCase: MainUseCase

    fun getListMovie(genre : String) {
        viewModelScope.launch {
            withContext(dispatcher) {
                val content = mUseCase.getMovieUsingGenre(genre)
                if (content.success!!) {
                    movie.postValue(content.datas)
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