package com.coronapp.repositories

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.quarantine.country/api/"

val retrofitClient : QuarantineInterface by lazy {
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return@lazy retrofit.create(QuarantineInterface::class.java)
}