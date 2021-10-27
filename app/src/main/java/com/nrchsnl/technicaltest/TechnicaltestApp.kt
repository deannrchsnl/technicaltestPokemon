package com.nrchsnl.technicaltest

/**
 * Created by deannrchsnl on 25/10/21.
 */
import android.app.Application
import android.content.Context

class TechnicaltestApp : Application() {
    override fun onCreate() {
        super.onCreate()

        TechnicaltestApp.appContext = applicationContext
    }

    companion object {

        var appContext: Context? = null
            private set

        operator fun get(context: Context): TechnicaltestApp {
            return context.applicationContext as TechnicaltestApp
        }
    }
}