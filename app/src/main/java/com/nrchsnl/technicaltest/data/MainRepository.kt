package com.nrchsnl.technicaltest.data

import com.nrchsnl.technicaltest.data.remote.model.Datas
import com.nrchsnl.technicaltest.data.remote.model.Result
import com.nrchsnl.technicaltest.data.remote.model.detail.ReturnDetail
import com.nrchsnl.technicaltest.data.remote.model.genre.Genre
import com.nrchsnl.technicaltest.data.remote.model.review.Rating
import com.nrchsnl.technicaltest.data.remote.model.video.ReturnVideo

interface MainRepository {

    suspend fun getNowPlaying(token: String): Result<Datas>
    suspend fun getGenre(token: String): Result<Genre>
    suspend fun getTopRated(token: String): Result<Datas>
    suspend fun getPopular(token: String): Result<Datas>
    suspend fun getComingsoon(token: String): Result<Datas>
    suspend fun getTrending(token: String): Result<Datas>
    suspend fun getDetail(id : String, token: String): ReturnDetail
    suspend fun getVideo(id : String, token: String): Result<ReturnVideo>
    suspend fun getRating(id : String, token: String): Result<Rating>
    suspend fun getMovieUsingGenre(token: String, genre : String): Result<Datas>
    suspend fun searchMovie(token: String, query : String): Result<Datas>
}