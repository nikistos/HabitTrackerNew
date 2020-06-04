package com.example.habittrackernew.editor

import android.util.Log
import android.widget.Toast
import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.DomainHabit
import com.example.domain.usecases.HabitProcessingService
import kotlinx.coroutines.*
import java.util.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


class EditorViewModel (val useCases: HabitProcessingService, habitId: String?) :
    ViewModel(), CoroutineScope {

    val editableHabit = if (habitId == null) DomainHabit() else useCases.getHabitById(habitId)

    private val isNewHabit = (habitId == null)

    private val _eventEditionFinished = MutableLiveData<Boolean>()
    val eventEditionFinished: LiveData<Boolean>
        get() = _eventEditionFinished

    private val _showErrorToast: MutableLiveData<Boolean> = MutableLiveData()
    val showErrorToast: LiveData<Boolean>
        get() = _showErrorToast


    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + Job()


    override fun onCleared() {
        super.onCleared()
        coroutineContext.cancelChildren()
    }

        fun saveHabit() = viewModelScope.launch {
        setEditTime()
        try {
            if (isNewHabit)
                useCases.addHabit(editableHabit)
            else useCases.updateHabit(editableHabit)
            _showErrorToast.value = false
        } catch (e: Exception) {
            _showErrorToast.value = true
        }
        _eventEditionFinished.value = true
    }

    private fun setEditTime() {
        editableHabit.date = (Date().time / 1000).toInt()
    }
}