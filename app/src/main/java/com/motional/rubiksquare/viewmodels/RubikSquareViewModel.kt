package com.motional.rubiksquare.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.motional.rubiksquare.NeighborPosition
import com.motional.rubiksquare.RubikHelperImpl
import com.motional.rubiksquare.data.Cell
import com.motional.rubiksquare.data.CellState

class RubikSquareViewModel : ViewModel() {

    val numPlayers: MutableLiveData<Int> = MutableLiveData(1)
    val cellsFirstPlayer: MutableLiveData<List<Cell>> = MutableLiveData()
    val cellsSecondPlayer: MutableLiveData<List<Cell>> = MutableLiveData()
    val whichPlayerTurn: MutableLiveData<Int> = MutableLiveData(1)
    private val rubikHelper = RubikHelperImpl()

    val playerOneClickListener = { cell: Cell ->
        val cells = cellsFirstPlayer.value
            ?: throw IllegalArgumentException("cellsFirstPlayer cannot be null at this point")

        setStatesOfCells(cells, cell)

        cellsFirstPlayer.value = cells

        val copyList = cells.map { it.copy() }
        copyList[(cell.pos)].state = CellState.NeigborSelected
        cellsSecondPlayer.value = copyList

        whichPlayerTurn.value = if (numPlayers.value == 2) 2 else 1
    }

    val playerTwoClickListener = { cell: Cell ->
        val cells = cellsSecondPlayer.value
            ?: throw IllegalArgumentException("cellsFirstPlayer cannot be null at this point")

        setStatesOfCells(cells, cell)

        cellsSecondPlayer.value = cells

        val copyList = cells.map { it.copy() }
        copyList[(cell.pos)].state = CellState.NeigborSelected
        cellsFirstPlayer.value = copyList

        whichPlayerTurn.value = 1
    }

    private fun setStatesOfCells(cells: List<Cell>, currentCell: Cell) {
        // Reset states
        cells.forEach {
            it.state = CellState.None
        }

        cells[(currentCell.pos)].numToDisplay = rubikHelper.incrementNumToDisplay(currentCell.numToDisplay)
        cells[(currentCell.pos)].state = CellState.Selected
        setStateOfNeighbor(NeighborPosition.Top, cells, currentCell)
        setStateOfNeighbor(NeighborPosition.Bottom, cells, currentCell)
        setStateOfNeighbor(NeighborPosition.Left, cells, currentCell)
        setStateOfNeighbor(NeighborPosition.Right, cells, currentCell)
    }

    private fun setStateOfNeighbor(pos: NeighborPosition, cells: List<Cell>, currentCell: Cell) {
        if (rubikHelper.checkNeighborValid(currentCell, pos)) {
            val neighborCell = cells[(currentCell.pos + pos.stepsFromCell)]
            neighborCell.numToDisplay = rubikHelper.incrementNumToDisplay((neighborCell.numToDisplay))
            neighborCell.state = CellState.NeigborSelected
        }
    }

    fun resetCells() {
        val cells = rubikHelper.generateRandomCells()
        cellsFirstPlayer.value = cells
        val copyList = cells.map { it.copy() }
        cellsSecondPlayer.value = copyList
        whichPlayerTurn.value = 1
    }

    fun isGoalReached() = rubikHelper.checkAllValuesSame(cellsFirstPlayer.value!!)

    fun endGame() {
        val cells = List(9) { 1 }.mapIndexed { index, i -> Cell(index, i) }
        cellsFirstPlayer.value = cells
    }
}