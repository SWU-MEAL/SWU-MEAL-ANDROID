package com.example.swubab.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseTodaySwubabDto(
    @SerialName("code")
    val code: Int?,
    @SerialName("message")
    val message: String?,
    @SerialName("data")
    val `data`: Data?
) {
    @Serializable
    data class Data(
        @SerialName("date")
        val date: String,
        @SerialName("time")
        val time: String,
        @SerialName("result")
    val result: List<Result?>?
    ) {
        @Serializable
        data class Result(
            @SerialName("corner")
            val corner: String?,
            @SerialName("items")
            val items: List<String?>?,
            @SerialName("menuId")
            val menuId: Int?,
            @SerialName("type")
            val type: String?
        )
    }
}