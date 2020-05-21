package com.example.habittrackernew.editor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.habittrackernew.R
import com.example.habittrackernew.database.*
import com.example.habittrackernew.databinding.FragmentEditorBinding
import kotlinx.android.synthetic.main.fragment_editor.*

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
            if (finished) findNavController().navigate(R.id.action_editorFragment_to_habitViewPagerFragment)
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

        var adapter = ArrayAdapter(
            requireContext(),
            R.layout.dropdown_menu_popup_item,
            resources.getStringArray(R.array.habitPriorities)
        )

        adapter.filter.apply {

        }

        binding.textPriority.setAdapter(adapter)

        adapter = ArrayAdapter(
            requireContext(),
            R.layout.dropdown_menu_popup_item,
            resources.getStringArray(R.array.habitPeriods)
        )
        binding.textPeriod.setAdapter(adapter)
            }

}