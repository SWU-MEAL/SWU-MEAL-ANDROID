package com.swubab.data.api

import com.swubab.data.dto.BaseResponseNullable
import com.swubab.data.dto.response.ResponseWeekSwuBabDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeekSwubabApiService {
    @GET("/v1/menu")
    suspend fun getWeekSwubab(
        @Query(value = "date") date: String
    ): BaseResponseNullable<ResponseWeekSwuBabDto>
}