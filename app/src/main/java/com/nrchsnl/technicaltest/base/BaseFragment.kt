package com.nrchsnl.technicaltest.base

import androidx.lifecycle.Observer
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.nrchsnl.technicaltest.base.MessageType.*
/**
 * Created by deannrchsnl on 25/10/21.
 */

abstract class BaseFragment<T : BaseViewModel> : Fragment() {

    private lateinit var mParentVM: T
    private var mMessageType = MESSAGE_TYPE_SNACK

    override fun onCreate(savedInstanceState: Bundle?) {
        onCreateInjector()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutRes(), container, false)
    }

    override fun onViewCreated(paramView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(paramView, savedInstanceState)
        mParentVM = onCreateViewModel().apply {
            eventGlobalMessage.observe(this@BaseFragment, Observer {
                it?.let { showMessage(it) }
            })
            eventGlobalMessageRes.observe(this@BaseFragment, Observer {
                it?.let { showMessage(getString(it)) }
            })
        }

        onCreateObserver(mParentVM)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setContentData()
        mParentVM.start()
    }

    fun setMessageType(type: MessageType) {
        mMessageType = type
    }

    private fun showMessage(message: String) {
        Toast.makeText(
            requireContext(), message, Toast.LENGTH_SHORT
        ).show()
    }

    abstract fun getLayoutRes(): Int
    abstract fun onCreateInjector()
    abstract fun onCreateViewModel(): T
    abstract fun onCreateObserver(viewModel: T)
    abstract fun setContentData()
}