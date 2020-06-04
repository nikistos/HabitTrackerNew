package com.example.habittrackernew.list_of_habits

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.domain.usecases.HabitProcessingService
import javax.inject.Inject

class ListViewModelFactory constructor (
    private val useCases: HabitProcessingService
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ListViewModel(useCases) as T
    }
}