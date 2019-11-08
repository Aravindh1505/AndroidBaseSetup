package com.aravindh.andriodbasesetup.ui.photos.viewphotos

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aravindh.andriodbasesetup.database.entities.Photos

class ViewPhotosViewModel(photos: Photos, application: Application) :
    AndroidViewModel(application) {

    private val _selectedPhoto = MutableLiveData<Photos>()
    val selectedPhoto: LiveData<Photos>
        get() = _selectedPhoto

    init {
        _selectedPhoto.value = photos
    }
}