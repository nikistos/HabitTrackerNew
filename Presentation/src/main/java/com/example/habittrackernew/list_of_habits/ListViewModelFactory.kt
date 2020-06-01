package com.example.habittrackernew.list_of_habits

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.domain.usecases.HabitProcessingService

class ListViewModelFactory(
    private val useCases: HabitProcessingService,
    private val token: String
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ListViewModel(useCases, token) as T
    }
}