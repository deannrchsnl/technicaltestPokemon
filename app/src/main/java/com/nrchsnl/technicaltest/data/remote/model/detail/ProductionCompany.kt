package com.nrchsnl.technicaltest.data.remote.model.detail

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

data class ProductionCompany (
    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("logo_path")
    var logoPath: Any? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("origin_country")
    var originCountry: String? = null
)