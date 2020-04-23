package com.example.habittrackernew.list_of_habits

import androidx.lifecycle.ViewModel
import com.example.habittrackernew.database.HabitDao
import com.example.habittrackernew.database.HabitEntity
import com.example.habittrackernew.database.HabitType

class ListViewModel(database: HabitDao) : ViewModel() {

    var habits = database.getAllHabits()

    fun getSortedHabitList(type: HabitType): List<HabitEntity> {
        return when (type) {
            HabitType.Good -> habits.value?.filter { habitEntity -> habitEntity.type == HabitType.Good }
                ?: listOf()
            else -> habits.value?.filter { habitEntity -> habitEntity.type == HabitType.Bad }
                ?: listOf()
        }
    }

}