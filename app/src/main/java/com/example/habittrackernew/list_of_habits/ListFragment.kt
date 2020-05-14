package com.example.habittrackernew.list_of_habits

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.habittrackernew.R
import com.example.habittrackernew.database.AppDataBase
import com.example.habittrackernew.database.HabitType
import com.example.habittrackernew.databinding.FragmentListRecyclerViewBinding
import kotlinx.android.synthetic.main.fragment_list_recycler_view.*
import java.lang.Appendable

class ListFragment : Fragment() {

    companion object {
        const val GOOD_HABITS = "GOOD_HABITS"
        const val BAD_HABITS = "BAD_HABITS"
        private const val HABIT_TYPE = "HABIT_TYPE"
        fun getNewInstance(type: String) = ListFragment()
            .apply { arguments = bundleOf(HABIT_TYPE to type) }
    }

    lateinit var binding: FragmentListRecyclerViewBinding
    lateinit var viewModel: ListViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val database = AppDataBase.getInstance(requireContext()).habitDao()
        val token = resources.getString(R.string.authToken)
        val viewModelFactory = ListViewModelFactory(database, token)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ListViewModel::class.java)

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_list_recycler_view,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val habitType = arguments?.getString(HABIT_TYPE) ?: GOOD_HABITS
        val adapter = HabitRecyclerViewAdapter()
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