package com.nrchsnl.technicaltest.base

/**
 * Created by deannrchsnl on 25/10/21.
 */
import android.util.Log
import androidx.lifecycle.ViewModel
import com.nrchsnl.technicaltest.helper.SingleLiveEvent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel(
    val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel(), CoroutineScope {

    private val supervisorJob = SupervisorJob()

    val eventShowProgress = SingleLiveEvent<Boolean>()
    val eventGlobalMessage = SingleLiveEvent<String>()
    val eventGlobalMessageRes = SingleLiveEvent<Int>()

    fun showLogDebug(TAG: String, data: String) = Log.d(TAG, data)
    fun showLogVerbose(TAG: String, data: String) = Log.v(TAG, data)
    fun showLogError(TAG: String, errorMessage: String) = Log.e(TAG, errorMessage)

    override val coroutineContext: CoroutineContext
        get() = dispatcher + supervisorJob

    open fun start() {}

    open fun onClearDisposable() {
        supervisorJob.cancel()
    }

    override fun onCleared() {
        supervisorJob.cancel()
        super.onCleared()
    }
}