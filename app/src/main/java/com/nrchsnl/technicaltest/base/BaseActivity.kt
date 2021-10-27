package com.nrchsnl.technicaltest.base

/**
 * Created by deannrchsnl on 25/10/21.
 */
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.youtube.player.YouTubeBaseActivity

abstract class BaseActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutRes())
    }

    override fun onStart() {
        super.onStart()
        setContentData()
    }

    abstract fun getLayoutRes(): Int
    abstract fun setContentData()
}