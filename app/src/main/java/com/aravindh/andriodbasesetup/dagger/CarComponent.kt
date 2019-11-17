package com.aravindh.andriodbasesetup.dagger

import dagger.Component

@Component(modules = [WheelsModule::class])
interface CarComponent {

    fun inject(daggerActivity: DaggerActivity)
}
