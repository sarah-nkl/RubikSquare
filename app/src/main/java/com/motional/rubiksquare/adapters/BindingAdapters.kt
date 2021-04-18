package com.motional.rubiksquare.adapters

import android.widget.CheckedTextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.motional.rubiksquare.data.Cell

@BindingAdapter("bindList")
fun RecyclerView.bindList(cells: List<Cell>?) {
    cells ?: return
    (adapter as? CubeAdapter)?.updateList(cells)
}

@BindingAdapter("cellSelected")
fun CheckedTextView.cellSelected(isSelected: Boolean) {
    setSelected(isSelected)
}
