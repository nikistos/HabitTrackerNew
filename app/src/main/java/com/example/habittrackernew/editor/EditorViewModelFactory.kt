package com.example.habittrackernew.editor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.habittrackernew.database.HabitDao

class EditorViewModelFactory(
    private val database: HabitDao,
    private val habitId: String? = null,
    private val token: String
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EditorViewModel(database, habitId, token) as T
    }
}