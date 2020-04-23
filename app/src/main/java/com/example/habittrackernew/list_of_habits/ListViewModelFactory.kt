package com.example.habittrackernew.list_of_habits

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.habittrackernew.database.HabitDao

class ListViewModelFactory(private val database: HabitDao): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ListViewModel(database) as T
    }
}