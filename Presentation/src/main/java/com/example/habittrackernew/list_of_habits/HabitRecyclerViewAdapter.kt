package com.example.habittrackernew.list_of_habits

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.DomainHabit
import com.example.domain.usecases.HabitProcessingService
import com.example.habittrackernew.R
import com.example.habittrackernew.databinding.LayoutRecyclerViewHabitItemBinding
import com.example.habittrackernew.editor.EditorFragment
import com.example.habittrackernew.generated.callback.OnClickListener

class HabitRecyclerViewAdapter(private val habitItemActions: HabitItemActions) :
    RecyclerView.Adapter<HabitRecyclerViewAdapter.ViewHolder>() {
    var data = listOf<DomainHabit>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(private val binding: LayoutRecyclerViewHabitItemBinding, private val habitItemActions: HabitItemActions) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(habit: DomainHabit) {
            binding.habit = habit
            binding.buttonCheck.setOnClickListener{
                habitItemActions.doHabit(data[adapterPosition].uid)
            }
            binding.habitItemBody.setOnClickListener{
                habitItemActions.openHabit(data[this.layoutPosition].uid)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutRecyclerViewHabitItemBinding.inflate(inflater)
        return ViewHolder(binding, habitItemActions)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])

    }
}

