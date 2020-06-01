package com.example.habittrackernew.list_of_habits

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.DomainHabit
import com.example.habittrackernew.R
import com.example.habittrackernew.databinding.LayoutRecyclerViewHabitItemBinding
import com.example.habittrackernew.editor.EditorFragment

class HabitRecyclerViewAdapter() : RecyclerView.Adapter<HabitRecyclerViewAdapter.ViewHolder>() {
    var data = listOf<DomainHabit>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(val binding: LayoutRecyclerViewHabitItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(habit: DomainHabit) {
            binding.habit = habit
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutRecyclerViewHabitItemBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])

        holder.itemView.setOnClickListener() {
            it.findNavController().navigate(
                R.id.action_habitViewPagerFragment_to_editorFragment,
                EditorFragment.getBundleWithHabitId(data[position].uid)
            )
        }
    }
}

