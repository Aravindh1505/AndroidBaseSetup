package com.aravindh.andriodbasesetup.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aravindh.andriodbasesetup.database.entities.Photos
import com.aravindh.andriodbasesetup.network.API

class MainRepository {

    private var _photos = MutableLiveData<List<Photos>>()
    val photos: LiveData<List<Photos>>
        get() = _photos


    suspend fun getPhotos() {
        val photoList = API.retrofitApiService.getPhotosAsync().await()

        _photos.value = photoList
    }


}