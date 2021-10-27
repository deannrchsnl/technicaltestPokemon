package com.nrchsnl.technicaltest.ui.search

import android.os.Bundle
import com.nrchsnl.technicaltest.R
import com.nrchsnl.technicaltest.base.BaseActivity
import com.nrchsnl.technicaltest.ui.genre.GenreFragment

class SearchActivity : BaseActivity() {
    override fun getLayoutRes(): Int = R.layout.activity_home

    override fun setContentData() {
        showFragment()
    }


    private fun showFragment() {
        val bundle = Bundle()
        val fragment = SearchFragment.newInstance(bundle)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
}