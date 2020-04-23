package com.example.habittrackernew.editor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habittrackernew.database.HabitDao
import com.example.habittrackernew.database.HabitEntity
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class EditorViewModel(val database: HabitDao, habitId: Int?) : ViewModel(), CoroutineScope {

    var editableHabit = if (habitId != null)
        database.getHabitById(habitId)
    else MutableLiveData(HabitEntity())
    val isNewHabit = (habitId == null)

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + Job()

    override fun onCleared() {
        super.onCleared()
        coroutineContext.cancelChildren()
    }

    fun saveHabit() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            if (isNewHabit)
                database.insert(editableHabit.value!!)
            else database.update(editableHabit.value!!)
//            editableHabit.value?.let {
//                if (isNewHabit)
//                    database.insert(it)
//                else database.update(it)
//            }
        }
    }

}