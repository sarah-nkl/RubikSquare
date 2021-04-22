package com.motional.rubiksquare

import com.motional.rubiksquare.data.Cell

interface RubikHelper {
    fun incrementNumToDisplay(currNum: Int): Int
    fun checkNeighborValid(cell: Cell, neighborPosition: NeighborPosition): Boolean
    fun checkAllValuesSame(cells: List<Cell>): Boolean
    fun generateRandomCells(): List<Cell>
}

enum class NeighborPosition(val stepsFromCell: Int) {
    Top(-3),
    Left(-1),
    Right(1),
    Bottom(3)
}