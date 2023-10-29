package com.example.swubab.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseWeekSwuBabDto(
    @SerialName("date")
    val date: String,
    @SerialName("result")
    val result: List<MealData>
) {
    @Serializable
    data class MealData(
        @SerialName("time")
        val time: String,
        @SerialName("menuList")
        val menuList: List<Menu>
    ) {
        @Serializable
        data class Menu(
            @SerialName("menuId")
            val menuId: Int,
            @SerialName("type")
            val type: String,
            @SerialName("corner")
            val corners: String?,
            @SerialName("items")
            val items: List<String>
        )
    }
}