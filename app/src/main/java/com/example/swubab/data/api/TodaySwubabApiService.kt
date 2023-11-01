package com.example.swubab.data.api

import com.example.swubab.data.dto.BaseResponseNullable
import com.example.swubab.data.dto.response.ResponseTodaySwubabDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TodaySwubabApiService {
    @GET("/v1/menu/today")
    fun getTodayMenu(
       @Query("time") time: String
    ): Call<ResponseTodaySwubabDto>
}