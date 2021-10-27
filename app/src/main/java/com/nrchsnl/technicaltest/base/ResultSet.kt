package com.nrchsnl.technicaltest.base

import com.nrchsnl.technicaltest.data.remote.model.Meta

data class ResultSet<T>(
        val code: Int,
        val status: Boolean,
        val message: String,
        val data: T?,
        val meta: Meta? = Meta()
)