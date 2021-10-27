package com.nrchsnl.technicaltest.data.remote.model

import com.google.gson.annotations.SerializedName

/**
 * Created by deannrchsnl on 25/10/21.
 */
data class BaseResult<T>(
        @SerializedName("meta")
        val meta: Meta? = Meta(),
        @SerializedName("data")
        val data: T?
)