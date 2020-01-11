package com.aravindh.andriodbasesetup.learn

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aravindh.andriodbasesetup.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


/**
 *Created by Aravindh S on 10-01-2020.
 */

class CoroutineActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo)



    }

    suspend fun updateUI(){
        withContext(Dispatchers.Main){

        }
    }
}