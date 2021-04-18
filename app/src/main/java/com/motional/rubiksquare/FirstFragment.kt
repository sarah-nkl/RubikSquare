package com.motional.rubiksquare

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.motional.rubiksquare.adapters.CubeAdapter
import com.motional.rubiksquare.databinding.FragmentFirstBinding
import com.motional.rubiksquare.viewmodels.RubikSquareViewModel

/**
 * A [Fragment] to host the Rubik Square for Player 1.
 */
class FirstFragment : Fragment() {

    private val viewModel: RubikSquareViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentFirstBinding>(
            inflater,
            R.layout.fragment_first,
            container,
            false
        ).apply {
            this.viewModel = this@FirstFragment.viewModel
            lifecycleOwner = viewLifecycleOwner
        }

        if (viewModel.cellsFirstPlayer.value == null) {
            viewModel.resetCells()
        }
        val adapter = CubeAdapter(
            viewModel.cellsFirstPlayer.value
                ?: throw IllegalStateException("P1 cells should not be null at this point"),
            viewModel.playerOneClickListener
        )
        binding.rvFirstSquare.adapter = adapter

        return binding.root
    }
}