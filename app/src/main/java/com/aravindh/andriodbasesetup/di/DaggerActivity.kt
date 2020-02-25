package com.aravindh.andriodbasesetup.di

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aravindh.andriodbasesetup.R
import com.aravindh.andriodbasesetup.di.component.CarComponent
import com.aravindh.andriodbasesetup.di.component.DaggerCarComponent
import com.aravindh.andriodbasesetup.di.model.Car
import javax.inject.Inject

const val DAGGER = "Dagger"

class DaggerActivity : AppCompatActivity() {

    @Inject
    lateinit var car: Car

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dagger)

        val carComponent: CarComponent = DaggerCarComponent.create()
        carComponent.inject(this)

        // car = carComponent.car
        car.drive()
    }
}
