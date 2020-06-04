package com.example.habittrackernew.editor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecases.HabitProcessingService

class EditorViewModelFactory(
    private val useCases: HabitProcessingService,
    private val habitId: String? = null
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EditorViewModel(useCases, habitId) as T
    }
}