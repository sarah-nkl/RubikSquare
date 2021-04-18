package com.motional.rubiksquare.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.motional.rubiksquare.data.Cell
import com.motional.rubiksquare.databinding.RubikCellBinding

class CubeAdapter(
    var cells: List<Cell>,
    val clickListener: (Cell) -> Unit
) : RecyclerView.Adapter<CubeAdapter.CubeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CubeViewHolder {
        return CubeViewHolder(
            RubikCellBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CubeViewHolder, position: Int) {
        val cell = cells[position]
        holder.binding.setClickListener {
            clickListener(cell)
        }
        holder.bind(cell)
    }

    override fun getItemCount(): Int {
        return cells.size
    }

    fun updateList(cells: List<Cell>) {
        this.cells = cells
        notifyDataSetChanged()
    }

    class CubeViewHolder(
        val binding: RubikCellBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Cell) {
            binding.apply {
                cell = item
                executePendingBindings()
            }
        }
    }
}

