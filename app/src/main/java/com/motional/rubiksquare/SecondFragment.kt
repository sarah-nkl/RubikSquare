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
import com.motional.rubiksquare.databinding.FragmentSecondBinding
import com.motional.rubiksquare.viewmodels.RubikSquareViewModel

/**
 * A [Fragment] to host the Rubik Square for Player 2.
 */
class SecondFragment : Fragment() {

    private val viewModel: RubikSquareViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentSecondBinding>(
            inflater,
            R.layout.fragment_second,
            container,
            false
        ).apply {
            this.viewModel = this@SecondFragment.viewModel
            lifecycleOwner = viewLifecycleOwner
        }

        val adapter = CubeAdapter(
            viewModel.cellsSecondPlayer.value
                ?: throw IllegalStateException("P2 cells should not be null at this point"),
            viewModel.playerTwoClickListener
        )
        binding.rvSecondSquare.adapter = adapter

        return binding.root
    }
}