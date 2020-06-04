package com.example.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.domain.models.*

@Entity(tableName = "habits")
@TypeConverters(PriorityConverter::class, TypeHabitConverter::class, FrequencyConverter::class, ListConverter::class)
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
    var color: Int = 0,
    var done_dates: MutableList<Int> = mutableListOf()
)
