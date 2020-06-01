package com.example.habittrackernew.list_of_habits

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.domain.models.DomainHabit
import com.example.domain.models.HabitType
import com.example.domain.usecases.HabitProcessingService
import kotlinx.coroutines.*
import java.lang.Exception

class ListViewModel(private val useCases: HabitProcessingService, token: String) : ViewModel() {

    var habits = useCases.getHabitList().asLiveData()

    private val _showErrorToast: MutableLiveData<Boolean> = MutableLiveData()
    val showErrorToast: LiveData<Boolean>
        get() = _showErrorToast

    private val viewModelJob = SupervisorJob()

    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        refreshHabitListFromRepository()
    }

    fun getSortedHabitList(type: HabitType): List<DomainHabit> {
        return when (type) {
            HabitType.Good -> habits.value?.filter { habitEntity -> habitEntity.type == HabitType.Good }
                ?: listOf()
            else -> habits.value?.filter { habitEntity -> habitEntity.type == HabitType.Bad }
                ?: listOf()
        }
    }

    private fun refreshHabitListFromRepository() {
        viewModelScope.launch {
            try {
                useCases.refreshDatabase()
            } catch (e: Exception) {
                _showErrorToast.value = true
            }
        }
    }
}