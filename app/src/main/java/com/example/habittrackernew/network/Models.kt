package com.example.habittrackernew.network

import androidx.room.Ignore
import com.example.habittrackernew.database.HabitEntity
import com.example.habittrackernew.database.PriorityConverter
import com.example.habittrackernew.database.TypeHabitConverter
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.squareup.moshi.JsonQualifier

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
    val uid: String = ""
) {
    fun asLocalModel(): HabitEntity {
        return HabitEntity(
            uid = this.uid,
            title = this.title,
            date = this.date,
            description = this.description,
            frequency = this.frequency,
            priority = PriorityConverter().toEnum(this.priority),
            type = TypeHabitConverter().toEnum(this.type),
            count = this.count,
            color = this.color
        )
    }
}

