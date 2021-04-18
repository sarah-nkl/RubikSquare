package com.motional.rubiksquare

import com.motional.rubiksquare.data.Cell
import kotlin.random.Random

class RubikHelperImpl : RubikHelper {
    override fun incrementNumToDisplay(currNum: Int): Int {
        return currNum % 3 + 1
    }

    override fun checkAllValuesSame(cells: List<Cell>): Boolean {
        return cells.groupBy {
            it.numToDisplay
        }.size == 1
    }

    override fun checkNeighborValid(cell: Cell, neighborPosition: NeighborPosition): Boolean {
        return when(neighborPosition) {
            NeighborPosition.Left -> cell.pos - 1 >= 0 && (cell.pos - 1) % 3 == cell.pos % 3 - 1
            NeighborPosition.Bottom -> cell.pos + 3 < 9
            NeighborPosition.Right -> cell.pos + 1 < 9 && (cell.pos + 1) % 3 == cell.pos % 3 + 1
            NeighborPosition.Top -> cell.pos - 3 >= 0
        }
    }

    override fun generateRandomCells(): List<Cell> {
        val randomValues = List(9) { Random.nextInt(1, 4) }
        return randomValues.mapIndexed { index, i ->
            Cell(index, i)
        }
    }
}