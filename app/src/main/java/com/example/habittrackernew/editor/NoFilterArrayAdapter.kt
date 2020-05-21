package com.example.habittrackernew.editor

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Filter
import com.example.habittrackernew.R


class NoFilterArrayAdapter(context: Context, private var items: Array<String>) :
    ArrayAdapter<String>(context, R.layout.dropdown_menu_popup_item, items) {

    override fun getCount() = items.size

    override fun getItem(position: Int) = items[position]

    override fun getFilter() = filter

    private var filter: Filter = object : Filter() {

        override fun performFiltering(constraint: CharSequence?): Filter.FilterResults {
            val results = FilterResults()
            results.values = items
            results.count = items.size
            return results
        }

        override fun publishResults(constraint: CharSequence?, results: Filter.FilterResults) {
            notifyDataSetChanged()
        }
    }
}