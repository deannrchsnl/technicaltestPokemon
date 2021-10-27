package com.nrchsnl.technicaltest.data.remote.model

import com.google.gson.annotations.SerializedName

data class Meta(
        @SerializedName("code")
        val code: Int? = 0,
        @SerializedName("error")
        val error: String? = "",
        @SerializedName("message")
        val message: String? = ""
)