package com.nrchsnl.technicaltest.data.remote

import com.nrchsnl.technicaltest.data.remote.model.Datas
import com.nrchsnl.technicaltest.data.remote.model.Result
import com.nrchsnl.technicaltest.data.remote.model.detail.ReturnDetail
import com.nrchsnl.technicaltest.data.remote.model.genre.Genre
import com.nrchsnl.technicaltest.data.remote.model.review.Rating
import com.nrchsnl.technicaltest.data.remote.model.video.ReturnVideo
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by deannrchsnl on 25/10/21.
 */
interface MainService {

    /**
     * Get now playing movie
     */
    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("api_key") apiKey: String
    ): Result<Datas>

    /**
     * Get Genre
     */
    @GET("genre/movie/list")
    suspend fun getGenre(
        @Query("api_key") apiKey: String
    ): Result<Genre>

    /**
     * Get Top rated movie
     */
    @GET("movie/top_rated")
    suspend fun getTopRated(
        @Query("api_key") apiKey: String
    ): Result<Datas>

    /**
     * Get Popular movie
     */
    @GET("movie/popular")
    suspend fun getPopular(
        @Query("api_key") apiKey: String
    ): Result<Datas>

    /**
     * Get comingsoon movie
     */
    @GET("movie/upcoming")
    suspend fun getUpcoming(
        @Query("api_key") apiKey: String
    ): Result<Datas>

    /**
     * Get trending movie
     */
    @GET("trending/movie/day")
    suspend fun getTrending(
        @Query("api_key") apiKey: String
    ): Result<Datas>

    /**
     * Get Artist movie
     */
    @GET("person/popular")
    suspend fun getArtist(
        @Query("api_key") apiKey: String
    ): Result<Datas>

    /**
     * Get detail movie
     */
    @GET("movie/{id}")
    suspend fun getDetailMovie(
        @Path("id") id : String,
        @Query("api_key") apiKey: String
    ): ReturnDetail

    /**
     * Get video movie
     */
    @GET("movie/{id}/videos")
    suspend fun getVideoMovie(
        @Path("id") id : String,
        @Query("api_key") apiKey: String
    ): Result<ReturnVideo>

    /**
     * Get review movie
     */
    @GET("movie/{id}/reviews")
    suspend fun getReviewsMovie(
        @Path("id") id : String,
        @Query("api_key") apiKey: String
    ): Result<Rating>

    /**
     * Get review movie
     */
    @GET("discover/movie")
    suspend fun getMovieUsingGenre(
        @Query("api_key") apiKey: String,
        @Query("with_genres") genre: String
    ): Result<Datas>

    /**
     * Get review movie
     */
    @GET("search/movie")
    suspend fun searchMovie(
        @Query("api_key") apiKey: String,
        @Query("query") query: String
    ): Result<Datas>

}