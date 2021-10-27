package com.nrchsnl.technicaltest.data.remote.model.detail

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




data class SpokenLanguage (
    @SerializedName("english_name")
    var englishName: String? = null,

    @SerializedName("iso_639_1")
    var iso6391: String? = null,

    @SerializedName("name")
    var name: String? = null
)