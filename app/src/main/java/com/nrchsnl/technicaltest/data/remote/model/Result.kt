package com.nrchsnl.technicaltest.data.remote.model

import com.google.gson.annotations.SerializedName
import com.nrchsnl.technicaltest.data.remote.model.video.ReturnVideo

data class Result<T> (
    @SerializedName("dates")
    val dates: Dates? = null,

    @SerializedName("page")
    val page: Int? = 0,

    @SerializedName("results")
    val datas : List<T>? = null,

    @SerializedName("total_pages")
    val totalPages : Int? = 0,

    @SerializedName("total_results")
    val totalResults : Int? = 0,

    @SerializedName("status_code")
    val statusCode : Int? = 0,

    @SerializedName("status_message")
    val statusMessage : String? = "",

    @SerializedName("success")
    val success : Boolean? = true,

    @SerializedName("genres")
    val genres : List<T>? = null

)