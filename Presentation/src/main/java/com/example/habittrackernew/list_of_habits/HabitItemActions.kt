package com.example.habittrackernew.list_of_habits

interface HabitItemActions {
    fun openHabit(habitUid: String)
    fun doHabit(habitUid: String)
}