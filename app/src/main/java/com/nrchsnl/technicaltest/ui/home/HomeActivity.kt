package com.nrchsnl.technicaltest.ui.home

import android.os.Bundle
import com.nrchsnl.technicaltest.R
import com.nrchsnl.technicaltest.base.BaseActivity

class HomeActivity : BaseActivity() {
    override fun getLayoutRes(): Int = R.layout.activity_home

    override fun setContentData() {
        showFragment()
    }


    private fun showFragment() {
        val bundle = Bundle()
        val fragment = HomeFragment.newInstance(bundle)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
}