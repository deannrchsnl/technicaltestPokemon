package com.nrchsnl.technicaltest

import com.nrchsnl.technicaltest.data.MainRepository
import com.nrchsnl.technicaltest.data.remote.model.Datas
import com.nrchsnl.technicaltest.data.remote.model.Result
import com.nrchsnl.technicaltest.data.remote.model.detail.ReturnDetail
import com.nrchsnl.technicaltest.data.remote.model.genre.Genre
import com.nrchsnl.technicaltest.data.remote.model.review.Rating
import com.nrchsnl.technicaltest.data.remote.model.video.ReturnVideo
import com.nrchsnl.technicaltest.helper.extensions.getAPIKey
import java.util.*

class MainUseCase(private val repository: MainRepository) {

    private val token = getAPIKey()

    suspend fun getNowPlaying(): Result<Datas> {
        return repository.getNowPlaying(token)
    }

    suspend fun getGenre(): Result<Genre> {
        return repository.getGenre(token)
    }

    suspend fun getTopRated(): Result<Datas> {
        return repository.getTopRated(token)
    }

    suspend fun getPopular(): Result<Datas> {
        return repository.getPopular(token)
    }

    suspend fun getComingsoon(): Result<Datas> {
        return repository.getComingsoon(token)
    }

    suspend fun getTrending(): Result<Datas> {
        return repository.getTrending(token)
    }

    suspend fun getDetail(id : String): ReturnDetail {
        return repository.getDetail(id, token)
    }

    suspend fun getVideo(id : String): Result<ReturnVideo> {
        return repository.getVideo(id, token)
    }

    suspend fun getRating(id : String): Result<Rating> {
        return repository.getRating(id, token)
    }

    suspend fun getMovieUsingGenre(genre : String): Result<Datas> {
        return repository.getMovieUsingGenre(token, genre)
    }

    suspend fun searchMovie(query : String): Result<Datas> {
        return repository.searchMovie(token, query)
    }
}