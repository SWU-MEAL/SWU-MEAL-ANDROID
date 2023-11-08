package com.swubab.data.api

import com.swubab.data.dto.BaseResponseNullable
import com.swubab.data.dto.response.ResponseTodaySwubabDto
import retrofit2.http.GET
import retrofit2.http.Query

interface TodaySwubabApiService {
    @GET("/v1/menu/today")
    suspend fun getTodayMenu(
        @Query(value = "time") time: String
    ): BaseResponseNullable<ResponseTodaySwubabDto>
}