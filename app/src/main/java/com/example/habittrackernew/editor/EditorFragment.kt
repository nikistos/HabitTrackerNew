package com.example.habittrackernew.editor

import android.os.Bundle
import android.renderscript.RenderScript
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.RadioButton
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.habittrackernew.R
import com.example.habittrackernew.database.AppDataBase
import com.example.habittrackernew.database.HabitPriority
import com.example.habittrackernew.database.HabitType
import com.example.habittrackernew.databinding.FragmentEditorBinding
import kotlinx.android.synthetic.main.fragment_editor.*

class EditorFragment : Fragment() {

    companion object {
        private const val HABIT_ID = "HABIT_ID"
        fun getBundleWithHabitId(habitId: Int) = bundleOf(HABIT_ID to habitId)
    }

    private lateinit var viewModel: EditorViewModel
    private lateinit var binding: FragmentEditorBinding
    private var habitId: Int? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //arguments?.getInt(HABIT_ID)?.let { habitId = it }
        if (arguments!!.containsKey(HABIT_ID)) {
            habitId = arguments!!.getInt(HABIT_ID)
        }
        val database = AppDataBase.getInstance(requireContext()).habitDao()
        val factory = EditorViewModelFactory(database, habitId)
        viewModel = ViewModelProviders.of(this, factory).get(EditorViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_editor, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.habit = viewModel.editableHabit.value

        binding.buttonSaveHabit.setOnClickListener {
            readDataFromUi()
            viewModel.saveHabit()
            it.findNavController().navigate(R.id.action_editorFragment_to_habitViewPagerFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fillDropDownMenus()
    }

    private fun  readDataFromUi() {
        viewModel.editableHabit.value?.let {
            it.title = binding.textName.text.toString()
            it.description = binding.textDescription.text.toString()
            it.count = if (binding.textRepeatCount.text.toString().isNotEmpty()) binding.textRepeatCount.text.toString().toInt() else 1
            it.frequency = if (binding.textPeriod.text.toString().isNotEmpty()) binding.textPeriod.text.toString() else "день"

            val typeFromUi = getView()!!.findViewById<RadioButton>(binding.radioGroupType.checkedRadioButtonId).text.toString()
            it.type = when (typeFromUi) {
                resources.getString(R.string.GoodHeader) -> HabitType.Good
                else -> HabitType.Bad
            }

            val priorityFromUi = binding.textPriority.text.toString()
            val priorityArray = resources.getStringArray(R.array.habitPriorities)
            it.priority = when(priorityFromUi) {
                priorityArray[0] -> HabitPriority.Low
                priorityArray[1] -> HabitPriority.Middle
                else -> HabitPriority.High
            }
        }
    }

    private fun fillDropDownMenus() {
        val priorityArray = resources.getStringArray(R.array.habitPriorities)
        val periodArray = resources.getStringArray(R.array.habitPeriods)
        var adapter = ArrayAdapter(requireContext(), R.layout.dropdown_menu_popup_item, priorityArray)
        binding.textPriority.setAdapter(adapter)
        adapter = ArrayAdapter(requireContext(), R.layout.dropdown_menu_popup_item, periodArray)
        binding.textPeriod.setAdapter(adapter)
        val a = viewModel.editableHabit.value?.priority ?: HabitPriority.Low
        val currentPriority = when (a) {
            HabitPriority.High -> priorityArray[2]
            HabitPriority.Middle -> priorityArray[1]
            else -> priorityArray[0]
        }
        binding.textPriority.setText(currentPriority)
    }
}