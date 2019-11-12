package com.aravindh.andriodbasesetup.dagger

import com.aravindh.andriodbasesetup.utils.Logger
import javax.inject.Inject

class Remote @Inject constructor(){

    fun setListener(car : Car){
        Logger.d("Remote connected...")
    }

}