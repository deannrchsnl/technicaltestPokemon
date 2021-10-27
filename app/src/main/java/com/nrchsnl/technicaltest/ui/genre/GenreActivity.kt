package com.nrchsnl.technicaltest.ui.genre

import android.os.Bundle
import com.nrchsnl.technicaltest.R
import com.nrchsnl.technicaltest.base.BaseActivity

class GenreActivity : BaseActivity() {
    override fun getLayoutRes(): Int = R.layout.activity_home

    override fun setContentData() {
        showFragment()
    }


    private fun showFragment() {
        val bundle = Bundle()
        val fragment = GenreFragment.newInstance(bundle)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
}