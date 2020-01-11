package com.aravindh.andriodbasesetup.utils

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent


class MainLifeCycleOwner(lifeCycle: Lifecycle) : LifecycleObserver {

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