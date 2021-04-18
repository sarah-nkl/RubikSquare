package com.motional.rubiksquare

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.motional.rubiksquare.data.CellState.NeigborSelected
import com.motional.rubiksquare.data.CellState.None
import com.motional.rubiksquare.data.CellState.Selected
import com.motional.rubiksquare.viewmodels.RubikSquareViewModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test


/**
 * Cell positions
 * --------------
 * 0 1 2
 * 3 4 5
 * 6 7 8
 */
class RubikSquareViewModelUnitTest {

    @get:Rule
    var executorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private val underTest = RubikSquareViewModel()

    @Before
    fun setup() {
        underTest.resetCells()
    }

    @Test
    fun `check states of first cell and neighbors on click`() {
        val p1Cells = underTest.cellsFirstPlayer.value!!
        underTest.playerOneClickListener.invoke(p1Cells[0])

        assertEquals(NeigborSelected, p1Cells[3].state)
        assertEquals(NeigborSelected, p1Cells[1].state)
        assertEquals(Selected, p1Cells[0].state)
        assertEquals(None, p1Cells[4].state)
    }

    @Test
    fun `check states of cells in P2 square on P1 click`() {
        val p1Cells = underTest.cellsFirstPlayer.value!!
        underTest.playerOneClickListener.invoke(p1Cells[0])

        val p2Cells = underTest.cellsSecondPlayer.value
        assertNotNull(p2Cells)
        assertEquals(NeigborSelected, p2Cells!![3].state)
        assertEquals(NeigborSelected, p2Cells[1].state)
        assertEquals(NeigborSelected, p2Cells[0].state)
        assertEquals(None, p2Cells[4].state)
    }

    @Test
    fun `is P2 turn after P1 click`() {
        val p1Cells = underTest.cellsFirstPlayer.value!!
        underTest.numPlayers.value = 2
        underTest.playerOneClickListener.invoke(p1Cells[0])
        assertEquals(2, underTest.whichPlayerTurn.value)
    }

    @Test
    fun `is P1 turn after P2 click`() {
        val p2Cells = underTest.cellsSecondPlayer.value!!
        underTest.numPlayers.value = 2
        underTest.playerTwoClickListener.invoke(p2Cells[0])
        assertEquals(1, underTest.whichPlayerTurn.value)
    }

    @Test
    fun `is P1 turn after reset`() {
        val p1Cells = underTest.cellsSecondPlayer.value!!
        underTest.numPlayers.value = 2
        underTest.playerOneClickListener.invoke(p1Cells[0])
        assertEquals(2, underTest.whichPlayerTurn.value)
        underTest.resetCells()
        assertEquals(1, underTest.whichPlayerTurn.value)
    }

    @Test
    fun `is P1 turn after P1 click when only one player`() {
        val p1Cells = underTest.cellsSecondPlayer.value!!
        underTest.numPlayers.value = 1
        underTest.playerOneClickListener.invoke(p1Cells[0])
        assertEquals(1, underTest.whichPlayerTurn.value)
    }

    @Test
    fun `is goal reached when forced end game`() {
        underTest.endGame()
        assertTrue(underTest.isGoalReached())
    }
}