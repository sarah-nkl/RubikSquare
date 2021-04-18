package com.motional.rubiksquare

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.DialogFragment
import com.motional.rubiksquare.databinding.ActivityMainBinding
import com.motional.rubiksquare.viewmodels.RubikSquareViewModel

class MainActivity : AppCompatActivity(), CompletionDialogFragment.DialogListener {

    private val viewModel: RubikSquareViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
            this.viewModel = this@MainActivity.viewModel
            lifecycleOwner = this@MainActivity
            setClickListener {
                if (viewModel?.numPlayers?.value == 1) {
                    addPlayerTwo()
                } else {
                    removePlayerTwo()
                }
            }
            setSupportActionBar(this.toolbar)
        }
        subscribeUi()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_reset -> {
            viewModel.resetCells()
            true
        }
        R.id.action_end_game -> {
            viewModel.endGame()
            true
        }

        else -> super.onOptionsItemSelected(item)
    }

    private fun addPlayerTwo() {
        val secondFragment = SecondFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.second_fragment, secondFragment)
            .commit()
        viewModel.numPlayers.value = 2
    }

    private fun removePlayerTwo() {
        val secondFragment = supportFragmentManager.findFragmentById(R.id.second_fragment)
        supportFragmentManager.beginTransaction()
            .remove(secondFragment!!)
            .commit()
        viewModel.numPlayers.value = 1
    }

    private fun showCongratulationsDialog() {
        if (supportFragmentManager.findFragmentByTag(FRAGMENT_TAG) == null) {
            val dialog = CompletionDialogFragment()
            dialog.show(supportFragmentManager, FRAGMENT_TAG)
        }
    }

    override fun onDialogPositiveClick(dialog: DialogFragment) {
        viewModel.resetCells()
    }

    private fun subscribeUi() {
        viewModel.numPlayers.observe(this) { num ->
            viewModel.whichPlayerTurn.value = num
        }
        viewModel.cellsFirstPlayer.observe(this) {
            if (viewModel.isGoalReached()) showCongratulationsDialog()
        }
    }
}