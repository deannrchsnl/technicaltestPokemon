package com.nrchsnl.technicaltest.data.remote

import com.nrchsnl.technicaltest.data.remote.model.Datas
import com.nrchsnl.technicaltest.data.remote.model.Result
import com.nrchsnl.technicaltest.data.remote.model.detail.ReturnDetail
import com.nrchsnl.technicaltest.data.remote.model.genre.Genre
import com.nrchsnl.technicaltest.data.remote.model.review.Rating
import com.nrchsnl.technicaltest.data.remote.model.video.ReturnVideo
import com.nrchsnl.technicaltest.helper.extensions.getExceptionResponse
import com.nrchsnl.technicaltest.helper.extensions.getExceptionResponseDetail

class MainRemoteDataSource(private val service: MainService) {

    /**
     * Get review for edit
     */
    suspend fun getNowPlaying(token: String): Result<Datas> {
        return try {
            service.getNowPlaying(token)
        } catch (e: java.lang.Exception) {
            getExceptionResponse(e)
        }
    }
    /**
     * Get Genre
     */
    suspend fun getGenre(token: String): Result<Genre> {
        return try {
            service.getGenre(token)
        } catch (e: java.lang.Exception) {
            getExceptionResponse(e)
        }
    }

    /**
     * Get top rated
     */
    suspend fun getTopRated(token: String): Result<Datas> {
        return try {
            service.getTopRated(token)
        } catch (e: java.lang.Exception) {
            getExceptionResponse(e)
        }
    }

    /**
     * Get popular
     */
    suspend fun getPopular(token: String): Result<Datas> {
        return try {
            service.getPopular(token)
        } catch (e: java.lang.Exception) {
            getExceptionResponse(e)
        }
    }
    /**
     * Get upcoming
     */
    suspend fun getUpcoming(token: String): Result<Datas> {
        return try {
            service.getUpcoming(token)
        } catch (e: java.lang.Exception) {
            getExceptionResponse(e)
        }
    }
    /**
     * Get trending
     */
    suspend fun getTrending(token: String): Result<Datas> {
        return try {
            service.getTrending(token)
        } catch (e: java.lang.Exception) {
            getExceptionResponse(e)
        }
    }
    /**
     * Get artist
     */
    suspend fun getArtist(token: String): Result<Genre> {
        return try {
            service.getGenre(token)
        } catch (e: java.lang.Exception) {
            getExceptionResponse(e)
        }
    }

    /**
     * Get detail movie
     */
    suspend fun getDetailMovie(id : String, token: String): ReturnDetail {
        return try {
            service.getDetailMovie(id, token)
        } catch (e: java.lang.Exception) {
            getExceptionResponseDetail(e)
        }
    }

    /**
     * Get video movie
     */
    suspend fun getVideoMovie(id : String, token: String): Result<ReturnVideo> {
        return try {
            service.getVideoMovie(id, token)
        } catch (e: java.lang.Exception) {
            getExceptionResponse(e)
        }
    }
    /**
     * Get rating movie
     */
    suspend fun getRatingVideo(id : String, token: String): Result<Rating> {
        return try {
            service.getReviewsMovie(id, token)
        } catch (e: java.lang.Exception) {
            getExceptionResponse(e)
        }
    }

    /**
     * Get list movie from genre
     */
    suspend fun getMovieUsingGenre(token: String, genre : String): Result<Datas> {
        return try {
            service.getMovieUsingGenre(token, genre)
        } catch (e: java.lang.Exception) {
            getExceptionResponse(e)
        }
    }

    /**
     * Get Search movie
     */
    suspend fun searchMovie(token: String, query : String): Result<Datas> {
        return try {
            service.searchMovie(token, query)
        } catch (e: java.lang.Exception) {
            getExceptionResponse(e)
        }
    }
}