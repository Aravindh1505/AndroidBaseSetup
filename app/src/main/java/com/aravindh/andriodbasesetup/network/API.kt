package com.aravindh.andriodbasesetup.network

import com.aravindh.andriodbasesetup.utils.Constants
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


//OKHTTPCLIENT CONFIGURATION FOR READ/CONNECTION TIMEOUT
private val client: OkHttpClient = OkHttpClient.Builder()
    .readTimeout(Constants.READ_TIMEOUT, TimeUnit.SECONDS)
    .connectTimeout(Constants.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
    .build()

//MOSHI LIBRARY FOR CONVERT JOSN OBJECT INTO MODEL CLASS
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

//RETROFIT CONFIGURATION
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .client(client)
    .build()

//API SERVICE
object API {
    val retrofitApiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}