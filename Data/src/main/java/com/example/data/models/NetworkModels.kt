package com.example.data.models

import com.squareup.moshi.*

@JsonClass(generateAdapter = true)
data class Error(
    val code: Int = 0,
    val message: String = ""
)

@JsonClass(generateAdapter = true)
data class HabitUid(
    val uid: String
)

@JsonClass(generateAdapter = true)
data class NetworkHabit(
    val color: Int = 0,
    val count: Int = 0,
    val date: Int = 0,
    val description: String = "",
    val frequency: Int = 0,
    val priority: Int = 0,
    val title: String = "",
    val type: Int = 0,
    val uid: String? = null,
    val done_dates: Array<Int> = arrayOf()
)
