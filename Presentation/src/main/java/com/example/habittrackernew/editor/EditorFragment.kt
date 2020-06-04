package com.example.habittrackernew.editor

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.data.database.DatabaseModule
import com.example.data.repository.RepositoryModule
import com.example.habittrackernew.R
import com.example.habittrackernew.databinding.FragmentEditorBinding
import com.example.habittrackernew.di.AppComponent
import com.example.habittrackernew.di.DaggerAppComponent
import com.example.habittrackernew.di.HabitTrackerApplication

class EditorFragment : Fragment() {

    companion object {
        private const val HABIT_ID = "HABIT_ID"
        fun getBundleWithHabitId(habitId: String) = bundleOf(HABIT_ID to habitId)
    }

    private lateinit var viewModel: EditorViewModel
    private lateinit var binding: FragmentEditorBinding


    private var habitId: String? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        arguments?.getString(HABIT_ID)?.let { habitId = it }

        val habitService =
            (requireActivity().application as HabitTrackerApplication).appComponent.getHabitProcessingService()


        val factory = EditorViewModelFactory(habitService, habitId)
        viewModel = ViewModelProviders.of(this, factory).get(EditorViewModel::class.java)

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_editor,
            container,
            false
        )

        viewModel.eventEditionFinished.observe(viewLifecycleOwner, Observer { finished ->
            if (finished) this.findNavController()
                .navigate(R.id.action_editorFragment_to_habitViewPagerFragment)
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
