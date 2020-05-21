package com.example.habittrackernew.database

import android.renderscript.RenderScript
import androidx.room.TypeConverter

class PriorityConverter {
    @TypeConverter
    fun fromEnum(enum: HabitPriority) = enum.ordinal
    @TypeConverter
    fun toEnum(ordinal: Int) = HabitPriority.values().first {it.ordinal == ordinal}
}

class TypeHabitConverter {
    @TypeConverter
    fun fromEnum(enum: HabitType) = enum.ordinal
    @TypeConverter
    fun toEnum(ordinal: Int) = HabitType.values().first {it.ordinal == ordinal}
}

class FrequencyConverter {
    @TypeConverter
    fun fromEnum(enum: Frequecy) = enum.ordinal
    @TypeConverter
    fun toEnum(ordinal: Int) = Frequecy.values().first {it.ordinal == ordinal}
}