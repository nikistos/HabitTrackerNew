package com.example.habittrackernew.editor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.habittrackernew.database.HabitDao
import com.example.habittrackernew.database.HabitEntity
import com.example.habittrackernew.repository.HabitRepository
import kotlinx.coroutines.*
import java.util.*
import kotlin.coroutines.CoroutineContext

class EditorViewModel(private val database: HabitDao, habitId: String?, token: String) :
    ViewModel(), CoroutineScope {

    private val repository = HabitRepository(database, token)

    var editableHabit = if (habitId != null)
        database.getHabitById(habitId)
    else MutableLiveData(HabitEntity())

    val isNewHabit = (habitId == null)

    private val _eventEditionFinished = MutableLiveData<Boolean>()
    val eventEditionFinished: LiveData<Boolean>
        get() = _eventEditionFinished

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + Job()

    override fun onCleared() {
        super.onCleared()
        coroutineContext.cancelChildren()
    }

    fun saveHabit() = viewModelScope.launch {
        setEditTime()
        withContext(Dispatchers.IO) {
            if (isNewHabit)
                repository.addHabit(editableHabit.value!!)
            else repository.updateHabit(editableHabit.value!!)
        }
        _eventEditionFinished.value = true
    }

    private fun setEditTime() {
        editableHabit.value?.date = (Date().time/1000).toInt()
    }
}