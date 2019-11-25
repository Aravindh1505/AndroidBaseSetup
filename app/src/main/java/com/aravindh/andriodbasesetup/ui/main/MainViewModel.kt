package com.aravindh.andriodbasesetup.ui.main

import androidx.lifecycle.ViewModel
import com.aravindh.andriodbasesetup.utils.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val mainRepository = MainRepository()

    init {
        Logger.d("MainViewModel initiated...")

        getPhotos()
    }

    val photos = mainRepository.photos


    private fun getPhotos() {
        coroutineScope.launch {
            mainRepository.getPhotos()

        }

    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}