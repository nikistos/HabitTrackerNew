package com.example.habittrackernew.binding_methods

import androidx.databinding.InverseMethod
import com.example.domain.models.*
import com.example.habittrackernew.R
import kotlinx.android.synthetic.main.fragment_editor.view.*

//TODO(Разобраться почему inverse не работает с livedata)
object EditorBindingMethods {

    @InverseMethod("buttonIdToHabitType")
    fun habitTypeToButtonId(type: HabitType) = when (type) {
        HabitType.Good -> R.id.radioGood
        else -> R.id.radioBad
    }

    fun buttonIdToHabitType(selectedButtonId: Int) = when (selectedButtonId) {
        R.id.radioGood -> HabitType.Good
        else -> HabitType.Bad
    }

    @InverseMethod("textToFrequency")
    fun frequencyToText(frequency: Frequecy) = frequency.toString()
    fun textToFrequency(text: String) = Frequecy.valueOf(text)

    @InverseMethod("textToHabitCount")
    fun habitCountToText(count: Int) = count.toString()
    fun textToHabitCount(text: String) = if (text.isEmpty()) 1 else text.toInt()

    @InverseMethod("textToPriority")
    fun priorityToText(priority: HabitPriority) = priority.toString()
    fun textToPriority(text: String) = HabitPriority.valueOf(text)
}






