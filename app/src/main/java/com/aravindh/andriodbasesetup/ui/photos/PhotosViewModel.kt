package com.aravindh.andriodbasesetup.ui.photos

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.aravindh.andriodbasesetup.database.dao.PhotosDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PhotosViewModel(val photosDao: PhotosDao, application: Application) :
    AndroidViewModel(application) {


    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val photosRepository = PhotosRepository(photosDao)

    init {
        getPhotos()
    }

    val photosList = photosRepository.photoList
    val status = photosRepository.status


    private fun getPhotos() {
        coroutineScope.launch {
            photosRepository.getPhotos()
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}