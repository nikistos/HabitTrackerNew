package com.example.habittrackernew.list_of_habits

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.database.DatabaseModule
import com.example.data.repository.RepositoryModule
import com.example.domain.models.HabitType
import com.example.domain.usecases.HabitProcessingService
import com.example.habittrackernew.R
import com.example.habittrackernew.databinding.FragmentListRecyclerViewBinding
import com.example.habittrackernew.di.DaggerAppComponent
import com.example.habittrackernew.di.HabitTrackerApplication
import com.example.habittrackernew.editor.EditorFragment
import kotlinx.android.synthetic.main.fragment_list_recycler_view.*
import javax.inject.Inject

class ListFragment : Fragment() {

    companion object {
        const val GOOD_HABITS = "GOOD_HABITS"
        const val BAD_HABITS = "BAD_HABITS"
        private const val HABIT_TYPE = "HABIT_TYPE"
        fun getNewInstance(type: String) = ListFragment()
            .apply { arguments = bundleOf(HABIT_TYPE to type) }
    }

    lateinit var habitService: HabitProcessingService
    lateinit var binding: FragmentListRecyclerViewBinding
    lateinit var viewModel: ListViewModel



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val habitService =
            (requireActivity().application as HabitTrackerApplication).appComponent.getHabitProcessingService()
        val viewModelFactory = ListViewModelFactory(habitService)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ListViewModel::class.java)

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_list_recycler_view,
            container,
            false
        )

        viewModel.showErrorToast.observe(viewLifecycleOwner, Observer { toShow ->
            if (toShow) Toast.makeText(requireContext(), "Some problems", Toast.LENGTH_LONG).show()
        })

        viewModel.showHabitDoneMessage.observe(viewLifecycleOwner, Observer { toShow ->
            if (toShow) Toast.makeText(requireContext(), "Habit done!", Toast.LENGTH_LONG).show()
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val habitType = arguments?.getString(HABIT_TYPE) ?: GOOD_HABITS
        val adapter = HabitRecyclerViewAdapter(object: HabitItemActions {
            override fun openHabit(habitUid: String) {
                findNavController().navigate(
                    R.id.action_habitViewPagerFragment_to_editorFragment,
                    EditorFragment.getBundleWithHabitId(habitUid)
                )
            }
            override fun doHabit(habitUid: String) {
                viewModel.doHabit(habitUid)
            }
        })
        recyclerHabits.layoutManager = LinearLayoutManager(requireContext())
        recyclerHabits.adapter = adapter

        viewModel.habits.observe(viewLifecycleOwner, Observer {
            adapter.data = when(habitType) {
                GOOD_HABITS -> viewModel.getSortedHabitList(HabitType.Good)
                else -> viewModel.getSortedHabitList(HabitType.Bad)
            }
        })

    }
}