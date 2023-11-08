package com.swubab.data.api

import com.swubab.data.dto.BaseResponseNullable
import com.swubab.data.dto.response.ResponseWeekSwuBabDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeekSwubabApiService {
    companion object {
        const val V1 = "v1"
        const val MENU = "menu"
        const val DATE = "date"
    }

    @GET("/$V1/$MENU")
    suspend fun getWeekSwubab(
        @Query(value = DATE) date: String
    ): BaseResponseNullable<ResponseWeekSwuBabDto>
}