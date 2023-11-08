package com.swubab.data.api

import com.swubab.data.dto.BaseResponseNullable
import com.swubab.data.dto.response.ResponseTodaySwubabDto
import retrofit2.http.GET
import retrofit2.http.Query

interface TodaySwubabApiService {
    companion object {
        const val V1 = "v1"
        const val MENU = "menu"
        const val TODAY = "today"
        const val TIME = "time"
    }

    @GET("/$V1/$MENU/$TODAY")
    suspend fun getTodayMenu(
        @Query(value = TIME) time: String
    ): BaseResponseNullable<ResponseTodaySwubabDto>
}