package com.example.habittrackernew.list_of_habits

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class HabitViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val tabCount = 2

    override fun getItemCount() = tabCount

    override fun createFragment(position: Int) = when (position) {
        0 -> ListFragment.getNewInstance(ListFragment.GOOD_HABITS)
        else -> ListFragment.getNewInstance(ListFragment.BAD_HABITS)
    }

}