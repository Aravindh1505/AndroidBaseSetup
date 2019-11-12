package com.aravindh.andriodbasesetup.dagger

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil

import com.aravindh.andriodbasesetup.R
import com.aravindh.andriodbasesetup.databinding.ActivityDaggerBinding
import com.aravindh.andriodbasesetup.utils.Logger
import javax.inject.Inject

class DaggerActivity : AppCompatActivity() {

    @Inject
    lateinit var car: Car


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityDaggerBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_dagger)


        val carComponent = DaggerCarComponent.create()
        carComponent.inject(this)

        val c = car.drive("car")

        Logger.d("c : $c")

        binding.txtView.text = c
    }
}
