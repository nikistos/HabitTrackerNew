package com.example.habittrackernew.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.habittrackernew.R
import com.example.habittrackernew.network.NetworkHabit

@Entity(tableName = "habits")
@TypeConverters(PriorityConverter::class, TypeHabitConverter::class, FrequencyConverter::class)
data class HabitEntity(
    @PrimaryKey
    var uid: String = "",
    var title: String = "",
    var date: Int = 0,
    var description: String = "",
    var frequency: Frequecy = Frequecy.Day,
    var priority: HabitPriority = HabitPriority.Low,
    var type: HabitType = HabitType.Good,
    var count: Int = 0,
    var color: Int = 0
) {
    fun asNetworkModel(): NetworkHabit {
        return NetworkHabit(
            uid = if (this.uid.isEmpty()) null else this.uid,
            title = this.title,
            date = this.date,
            description = this.description,
            priority = PriorityConverter().fromEnum(this.priority),
            type = TypeHabitConverter().fromEnum(this.type),
            color = this.color,
            count = this.count,
            frequency = when (this.frequency) {
                Frequecy.Day -> 1
                Frequecy.Week -> 7
                Frequecy.Month -> 30
            }
        )
    }
}

