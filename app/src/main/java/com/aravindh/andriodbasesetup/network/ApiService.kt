package com.aravindh.andriodbasesetup.network

import com.aravindh.andriodbasesetup.database.entities.Photos
import com.aravindh.andriodbasesetup.model.LoginRequest
import com.aravindh.andriodbasesetup.model.LoginResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


//const val BASE_URL = "http://bloodambulance.com/loveapp/php/"
const val BASE_URL = "https://portalwebapi.sundram.com:4002/auth/v1/"


interface ApiService {
    @GET("fetch_all_images.php")
    fun getPhotosAsync(): Deferred<List<Photos>>

    @POST("login")
    fun loginAsync(@Body loginRequest: LoginRequest): Deferred<LoginResponse>

}

