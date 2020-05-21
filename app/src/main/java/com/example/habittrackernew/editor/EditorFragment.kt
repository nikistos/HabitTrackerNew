package com.example.habittrackernew.editor

import android.content.Context
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.habittrackernew.R
import com.example.habittrackernew.database.*
import com.example.habittrackernew.databinding.FragmentEditorBinding

class EditorFragment : Fragment() {

    companion object {
        private const val HABIT_ID = "HABIT_ID"
        fun getBundleWithHabitId(habitId: String) = bundleOf(HABIT_ID to habitId)
    }

    private lateinit var viewModel: EditorViewModel
    private lateinit var binding: FragmentEditorBinding
    private lateinit var database: HabitDao


    private var habitId: String? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        arguments?.getString(HABIT_ID)?.let { habitId = it }

        database = AppDataBase.getInstance(requireContext()).habitDao()
        val token = resources.getString(R.string.authToken)

        val factory = EditorViewModelFactory(database, habitId, token)
        viewModel = ViewModelProviders.of(this, factory).get(EditorViewModel::class.java)

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_editor,
            container,
            false
        )

        viewModel.eventEditionFinished.observe(viewLifecycleOwner, Observer { finished ->
            if (finished) this.findNavController().navigate(R.id.action_editorFragment_to_habitViewPagerFragment)
        })

        viewModel.showErrorToast.observe(viewLifecycleOwner, Observer { toShow ->
            if (toShow) Toast.makeText(requireContext(), "Some problems", Toast.LENGTH_LONG)
        })

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fillDropdownMenus()
    }

    private fun fillDropdownMenus() {

        var adapter = NoFilterArrayAdapter(
            requireContext(),
            resources.getStringArray(R.array.habitPriorities)
        )

        binding.textPriority.setAdapter(adapter)
        binding.textPriority.inputType = InputType.TYPE_NULL

        adapter = NoFilterArrayAdapter(
            requireContext(),
            resources.getStringArray(R.array.habitPeriods)
        )

        binding.textPeriod.setAdapter(adapter)
        binding.textPeriod.inputType = InputType.TYPE_NULL
    }
}
