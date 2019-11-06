package com.aravindh.andriodbasesetup.ui.photos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aravindh.andriodbasesetup.network.API
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PhotosViewModel : ViewModel() {


    //STATUS OF API CALL
    private val _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status

    private val _photosList = MutableLiveData<List<Photos>>()
    val photosList: LiveData<List<Photos>>
        get() = _photosList

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    init {
        getPhotos()
    }

    private fun getPhotos() {
        coroutineScope.launch {
            val getPhotosDeferred = API.retrofitApiService.getPhotosAsync()
            try {
                val response = getPhotosDeferred.await()

                _photosList.value = response
                _status.value = response.size.toString()
            } catch (e: Exception) {
                _status.value = "Failure : ${e.message}"
            }
        }
    }

    fun getRandomNumber(): Int = (0..100).random()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}