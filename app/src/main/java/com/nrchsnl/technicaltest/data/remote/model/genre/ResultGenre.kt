package com.nrchsnl.technicaltest.data.remote.model.genre

import com.google.gson.annotations.SerializedName

data class ResultGenre<T> (
    @SerializedName("status_code")
    val statusCode : Int? = 0,

    @SerializedName("status_message")
    val statusMessage : String? = "",

    @SerializedName("success")
    val success : Boolean? = true,

    @SerializedName("genres")
    val datas : List<T>? = null
)