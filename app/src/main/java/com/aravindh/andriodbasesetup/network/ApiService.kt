package com.aravindh.andriodbasesetup.network

import com.aravindh.andriodbasesetup.database.entities.Photos
import com.aravindh.andriodbasesetup.ui.movie.model.Data
import kotlinx.coroutines.Deferred
import retrofit2.http.GET


//const val BASE_URL = "http://bloodambulance.com/loveapp/php/"
const val BASE_URL = "http://www.mocky.io/v2/"


interface ApiService {
    @GET("fetch_all_images.php")
    fun getPhotosAsync(): Deferred<List<Photos>>


    @GET("5b700cff2e00005c009365cf")
    fun getFoodsAsync(): Deferred<Data>
}

