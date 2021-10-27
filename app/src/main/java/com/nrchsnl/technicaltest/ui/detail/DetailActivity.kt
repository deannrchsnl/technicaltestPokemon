package com.nrchsnl.technicaltest.ui.detail

import android.os.Bundle
import com.google.android.youtube.player.YouTubeBaseActivity
import com.nrchsnl.technicaltest.R
import com.nrchsnl.technicaltest.base.BaseActivity

class DetailActivity : BaseActivity() {
    override fun getLayoutRes(): Int = R.layout.activity_home

    override fun setContentData() {
        showFragment()
    }


    private fun showFragment() {
        val bundle = Bundle()
        val fragment = DetailFragment.newInstance(bundle)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
}