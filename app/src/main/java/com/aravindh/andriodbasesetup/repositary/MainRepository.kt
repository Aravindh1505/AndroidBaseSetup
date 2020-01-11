package com.aravindh.andriodbasesetup.repositary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aravindh.andriodbasesetup.database.entities.Photos
import com.aravindh.andriodbasesetup.network.API
import com.aravindh.andriodbasesetup.network.ApiStatus

class MainRepository {

    //PHOTO LIST
    private var _photos = MutableLiveData<List<Photos>>()
    val photos: LiveData<List<Photos>>
        get() = _photos

    //API STATUS
    private var _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status


    suspend fun getPhotos() {
        _status.value = ApiStatus.LOADING

        try {
            _photos.value = API.retrofitApiService.getPhotosAsync().await()

            _status.value = ApiStatus.SUCCESS
        } catch (e: Exception) {
            _status.value = ApiStatus.FAILURE
        }
    }
}