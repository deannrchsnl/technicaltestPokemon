package com.nrchsnl.technicaltest.data

import com.nrchsnl.technicaltest.data.remote.MainRemoteDataSource
import com.nrchsnl.technicaltest.data.remote.model.Datas
import com.nrchsnl.technicaltest.data.remote.model.Result
import com.nrchsnl.technicaltest.data.remote.model.detail.ReturnDetail
import com.nrchsnl.technicaltest.data.remote.model.genre.Genre
import com.nrchsnl.technicaltest.data.remote.model.review.Rating
import com.nrchsnl.technicaltest.data.remote.model.video.ReturnVideo

class MainRepositoryImp(
    private val mainRemoteDataSource: MainRemoteDataSource
) : MainRepository {

    override suspend fun getNowPlaying(token: String): Result<Datas> {
        return mainRemoteDataSource.getNowPlaying(token)
    }

    override suspend fun getGenre(token: String): Result<Genre> {
        return mainRemoteDataSource.getGenre(token)
    }

    override suspend fun getTopRated(token: String): Result<Datas> {
        return mainRemoteDataSource.getTopRated(token)
    }

    override suspend fun getPopular(token: String): Result<Datas> {
        return mainRemoteDataSource.getPopular(token)
    }

    override suspend fun getComingsoon(token: String): Result<Datas> {
        return mainRemoteDataSource.getUpcoming(token)
    }

    override suspend fun getTrending(token: String): Result<Datas> {
        return mainRemoteDataSource.getTrending(token)
    }

    override suspend fun getDetail(id: String, token: String): ReturnDetail {
        return mainRemoteDataSource.getDetailMovie(id, token)
    }

    override suspend fun getVideo(id: String, token: String): Result<ReturnVideo> {
        return mainRemoteDataSource.getVideoMovie(id, token)
    }

    override suspend fun getRating(id: String, token: String): Result<Rating> {
        return mainRemoteDataSource.getRatingVideo(id, token)
    }

    override suspend fun getMovieUsingGenre(token: String, genre: String): Result<Datas> {
        return mainRemoteDataSource.getMovieUsingGenre(token, genre)
    }

    override suspend fun searchMovie(token: String, query: String): Result<Datas> {
        return mainRemoteDataSource.searchMovie(token, query)
    }
}