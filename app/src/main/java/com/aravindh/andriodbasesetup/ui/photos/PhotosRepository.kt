package com.aravindh.andriodbasesetup.ui.photos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aravindh.andriodbasesetup.database.dao.PhotosDao
import com.aravindh.andriodbasesetup.database.entities.Photos
import com.aravindh.andriodbasesetup.network.API
import com.aravindh.andriodbasesetup.network.ApiStatus
import com.aravindh.andriodbasesetup.utils.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PhotosRepository(private val photosDao: PhotosDao) {

    val photoList: LiveData<List<Photos>> = photosDao.getPhotos()

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status


    suspend fun getPhotos() {
        withContext(Dispatchers.IO) {
            val count = photosDao.getCount()
            Logger.d("isEmpty : $count")


            if (count == 0) {
                try {
                    _status.postValue(ApiStatus.LOADING)
                    val photosList = API.retrofitApiService.getPhotosAsync().await()

                    photosDao.insert(photosList)
                    _status.postValue(ApiStatus.SUCCESS)
                } catch (e: Exception) {
                    _status.postValue(ApiStatus.FAILURE)
                }
            } else {
                _status.postValue(ApiStatus.SUCCESS)
            }
        }
    }
}