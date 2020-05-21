package com.example.habittrackernew.list_of_habits

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.habittrackernew.database.HabitDao
import com.example.habittrackernew.database.HabitEntity
import com.example.habittrackernew.database.HabitType
import com.example.habittrackernew.repository.HabitRepository
import kotlinx.coroutines.*
import java.lang.Exception

class ListViewModel(private val database: HabitDao, token: String) : ViewModel() {

    var habits = database.getAllHabits()
    private val repository = HabitRepository(database, token)

    private val _showErrorToast: MutableLiveData<Boolean> = MutableLiveData()
    val showErrorToast: LiveData<Boolean>
        get() = _showErrorToast

    private val viewModelJob = SupervisorJob()

    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        refreshHabitListFromRepository()
    }

    fun getSortedHabitList(type: HabitType): List<HabitEntity> {
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
                repository.refreshDatabase()
            } catch (e: Exception) {
                _showErrorToast.value = true
            }
        }
    }
}