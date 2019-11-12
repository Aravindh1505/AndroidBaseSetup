package com.aravindh.andriodbasesetup.dagger

import dagger.Component

@Component
interface CarComponent {

    fun inject(daggerActivity: DaggerActivity)
}
