package com.example.habittrackernew.network

import android.renderscript.RenderScript
import androidx.room.Ignore
import com.example.habittrackernew.database.*
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
    val uid: String? = null
) {
    fun asLocalModel(): HabitEntity {
        return HabitEntity(
            uid = this.uid ?: "",
            title = this.title,
            date = this.date,
            description = this.description,
            priority = PriorityConverter().toEnum(this.priority),
            type = TypeHabitConverter().toEnum(this.type),
            count = this.count,
            color = this.color,
            frequency = when (this.frequency) {
                30 -> Frequecy.Month
                7 -> Frequecy.Week
                else -> Frequecy.Day
            }
        )
    }
}

@JsonClass(generateAdapter = false)
data class HabitsGetResponse(
    val networkHabitList: List<NetworkHabit> = listOf(),
    val error: Error = Error()
)
