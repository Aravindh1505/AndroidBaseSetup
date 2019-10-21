package com.aravindh.andriodbasesetup.ui.main

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.aravindh.andriodbasesetup.utils.Logger

class MyLifeCycleOwner(lifeCycle: Lifecycle) : LifecycleObserver {

    init {
        lifeCycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onCreate() {
        Logger.d("onStart working...")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onResume() {
        Logger.d("onStop working...")
    }

}