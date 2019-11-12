package com.aravindh.andriodbasesetup.dagger

import javax.inject.Inject

class Car @Inject constructor(private val engine: Engine, private val wheels: Wheels) {

    fun drive(msg: String): String {
        return "$msg driving...!"
    }

    @Inject
    fun enableRemote(remote: Remote) {
        remote.setListener(this)
    }
}
