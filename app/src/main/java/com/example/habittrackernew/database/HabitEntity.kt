package com.example.habittrackernew.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.habittrackernew.R
import com.example.habittrackernew.network.NetworkHabit

@Entity(tableName = "habits")
@TypeConverters(PriorityConverter::class, TypeHabitConverter::class)
data class HabitEntity(
    @PrimaryKey
    var uid: String = "",
    var title: String = "",
    var date: Int = 0,
    var description: String = "",
    var frequency: Int = 0,
    var priority: HabitPriority = HabitPriority.Low,
    var type: HabitType = HabitType.Good,
    var count: Int = 0,
    var color: Int = 0
) {
    fun asNetworkModel(): NetworkHabit {
        return NetworkHabit(
            uid = this.uid,
            title = this.title,
            date = this.date,
            description = this.description,
            frequency = this.frequency,
            priority = PriorityConverter().fromEnum(this.priority),
            type = TypeHabitConverter().fromEnum(this.type),
            color = this.color,
            count = this.count
        )
    }
}

