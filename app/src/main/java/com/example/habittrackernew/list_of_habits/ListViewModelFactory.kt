package com.example.habittrackernew.list_of_habits

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.habittrackernew.database.HabitDao
import com.example.habittrackernew.repository.HabitRepository

class ListViewModelFactory(
    private val database: HabitDao,
    private val token: String
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ListViewModel(database, token) as T
    }
}