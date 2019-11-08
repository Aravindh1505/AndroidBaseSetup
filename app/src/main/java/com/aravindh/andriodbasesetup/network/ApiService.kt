package com.aravindh.andriodbasesetup.network

import com.aravindh.andriodbasesetup.database.entities.Photos
import kotlinx.coroutines.Deferred
import retrofit2.http.GET


const val BASE_URL = "http://bloodambulance.com/loveapp/php/"


interface ApiService {
    @GET("fetch_all_images.php")
    fun getPhotosAsync(): Deferred<List<Photos>>
}

