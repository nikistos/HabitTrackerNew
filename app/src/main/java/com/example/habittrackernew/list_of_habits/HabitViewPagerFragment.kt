package com.example.habittrackernew.list_of_habits

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.habittrackernew.R
import com.example.habittrackernew.databinding.FragmentListPagerBinding
import com.google.android.material.tabs.TabLayoutMediator

class HabitViewPagerFragment : Fragment() {

    private lateinit var binding: FragmentListPagerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_pager, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.pagerHabits.adapter = HabitViewPagerAdapter(this)

        TabLayoutMediator(binding.tabLayoutTypes, binding.pagerHabits) { tab, position ->
            tab.text = when (position) {
                0 -> resources.getString(R.string.GoodHeader)
                else -> resources.getString(R.string.BadHeader)
            }
        }.attach()

        binding.buttonAdd.setOnClickListener() {
            it.findNavController().navigate(R.id.action_habitViewPagerFragment_to_editorFragment)
        }
    }

}