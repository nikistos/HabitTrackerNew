package com.example.habittrackernew.list_of_habits

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.habittrackernew.R
import com.example.habittrackernew.databinding.FragmentFiltrationBottomSheetBinding

class FiltrationBottomSheetFragment: Fragment() {
    private lateinit var binding: FragmentFiltrationBottomSheetBinding
    private lateinit var viewModel: ListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_filtration_bottom_sheet, container, false)
        return binding.root
    }
}