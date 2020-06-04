package com.example.habittrackernew.list_of_habits

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.domain.models.DomainHabit
import com.example.domain.models.HabitDone
import com.example.domain.models.HabitType
import com.example.domain.usecases.HabitProcessingService
import kotlinx.coroutines.*
import java.lang.Exception
import java.util.*

class ListViewModel(private val useCases: HabitProcessingService) : ViewModel() {

    var habits = useCases.getHabitList().asLiveData()

    private val _showErrorToast: MutableLiveData<Boolean> = MutableLiveData()
    val showErrorToast: LiveData<Boolean>
        get() = _showErrorToast

    private val _showHabitDoneMessage: MutableLiveData<Boolean> = MutableLiveData()
    val showHabitDoneMessage: LiveData<Boolean>
        get() = _showHabitDoneMessage

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

    fun doHabit(habitUid: String) {
        val doneTime = (Date().time / 1000).toInt()
        val habitDone = HabitDone(doneTime, habitUid)
        viewModelScope.launch {
            try {
                useCases.processHabitDone(habitDone)
                _showHabitDoneMessage.value = true
            } catch (e: Exception) {
                _showErrorToast.value = true
            } finally {
                messageShown()
            }
        }
    }

    fun messageShown() {
        _showHabitDoneMessage.value = false
        _showErrorToast.value = true
    }
}