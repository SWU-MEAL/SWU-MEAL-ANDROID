package com.swubab.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseTodaySwubabDto(
    @SerialName("date")
    val date: String,
    @SerialName("time")
    val time: String,
    @SerialName("result")
    val result: List<Result>
) {
    @Serializable
    data class Result(
        @SerialName("menuId")
        val menuId: Int,
        @SerialName("type")
        val type: String,
        @SerialName("corner")
        val corner: String?,
        @SerialName("items")
        val items: List<String>
    )
}
