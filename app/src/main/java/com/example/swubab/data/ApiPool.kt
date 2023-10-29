package com.example.swubab.data

import com.example.swubab.BuildConfig.BASE_URL
import com.example.swubab.data.api.TodaySwubabApiService
import com.example.swubab.data.api.WeekSwubabApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import timber.log.Timber

object ApiPool {
    val getTodaySwubab = RetrofitPool.retrofit.create(TodaySwubabApiService::class.java)
    val getWeekSwubab = RetrofitPool.retrofit.create(WeekSwubabApiService::class.java)
}


object RetrofitPool {
    val retrofit: Retrofit by lazy {
        Timber.tag("dataerror").e("ghcnf")
        Retrofit.Builder()
            .baseUrl("http://15.164.192.106:10000" + "/")
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }
}