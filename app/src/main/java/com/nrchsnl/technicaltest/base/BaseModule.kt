package com.nrchsnl.technicaltest.base

/**
 * Created by deannrchsnl on 25/10/21.
 */
import com.google.gson.Gson
import dagger.Module
import dagger.Provides

@Module
class BaseModule {

    @Provides
    fun provideGson(): Gson {
        return Gson()
    }
}