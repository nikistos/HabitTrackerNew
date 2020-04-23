package com.example.habittrackernew.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.habittrackernew.R

@Entity(tableName = "habits")
@TypeConverters(PriorityConverter::class, TypeHabitConverter::class)
data class HabitEntity(
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0,
    var title: String = "",
    var date: Int = 0,
    var description: String = "",
    var frequency: String = "",
    var priority: HabitPriority = HabitPriority.Low,
    var type: HabitType = HabitType.Good,
    var count: Int = 0,
    var color: Int = 0
)