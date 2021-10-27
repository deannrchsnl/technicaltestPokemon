package com.nrchsnl.technicaltest.data.remote.model.detail

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

data class ProductionCountry (
    @SerializedName("iso_3166_1")
    var iso31661: String? = null,

    @SerializedName("name")
    var name: String? = null
)