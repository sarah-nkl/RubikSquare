package com.motional.rubiksquare

import com.motional.rubiksquare.data.Cell
import org.junit.Test

import org.junit.Assert.*

/**
 * Cell positions
 * --------------
 * 0 1 2
 * 3 4 5
 * 6 7 8
 */
class RubikHelperTest {

    private val underTest = RubikHelperImpl()

    @Test
    fun `increment from 2 to 3`() {
        val result = underTest.incrementNumToDisplay(1)
        assertEquals(2, result)
    }

    @Test
    fun `increment from 3 to 1`() {
        val result = underTest.incrementNumToDisplay(3)
        assertEquals(1, result)
    }

    @Test
    fun `increment from 1 to 2`() {
        val result = underTest.incrementNumToDisplay(1)
        assertEquals(2, result)
    }

    @Test
    fun `check if goal is reached is true when all cells are 1`() {
        val cells = List(9) { 1 }.mapIndexed { index, i ->
            Cell(index, i)
        }
        assertTrue(underTest.checkAllValuesSame(cells))
    }

    @Test
    fun `check valid cells generated for randomizer`() {
        val cells = underTest.generateRandomCells()
        cells.forEach { cell ->
            assertTrue(cell.numToDisplay in 1..4)
        }
        assertEquals(9, cells.size)
    }

    @Test
    fun `check valid cells of cell zero`() {
        val cellZero = Cell(0, 1)
        assertFalse(underTest.checkNeighborValid(cellZero, NeighborPosition.Left))
        assertTrue(underTest.checkNeighborValid(cellZero, NeighborPosition.Right))
        assertFalse(underTest.checkNeighborValid(cellZero, NeighborPosition.Top))
        assertTrue(underTest.checkNeighborValid(cellZero, NeighborPosition.Bottom))
    }

    @Test
    fun `check valid cells of cell one`() {
        val cellOne = Cell(1, 1)
        assertTrue(underTest.checkNeighborValid(cellOne, NeighborPosition.Left))
        assertTrue(underTest.checkNeighborValid(cellOne, NeighborPosition.Right))
        assertFalse(underTest.checkNeighborValid(cellOne, NeighborPosition.Top))
        assertTrue(underTest.checkNeighborValid(cellOne, NeighborPosition.Bottom))
    }

    @Test
    fun `check valid cells of cell two`() {
        val cellTwo = Cell(2, 1)
        assertTrue(underTest.checkNeighborValid(cellTwo, NeighborPosition.Left))
        assertFalse(underTest.checkNeighborValid(cellTwo, NeighborPosition.Right))
        assertFalse(underTest.checkNeighborValid(cellTwo, NeighborPosition.Top))
        assertTrue(underTest.checkNeighborValid(cellTwo, NeighborPosition.Bottom))
    }

    @Test
    fun `check valid cells of cell three`() {
        val cellThree = Cell(3, 1)
        assertFalse(underTest.checkNeighborValid(cellThree, NeighborPosition.Left))
        assertTrue(underTest.checkNeighborValid(cellThree, NeighborPosition.Right))
        assertTrue(underTest.checkNeighborValid(cellThree, NeighborPosition.Top))
        assertTrue(underTest.checkNeighborValid(cellThree, NeighborPosition.Bottom))
    }

    @Test
    fun `check valid cells of cell four`() {
        val cellFour = Cell(4, 1)
        assertTrue(underTest.checkNeighborValid(cellFour, NeighborPosition.Left))
        assertTrue(underTest.checkNeighborValid(cellFour, NeighborPosition.Right))
        assertTrue(underTest.checkNeighborValid(cellFour, NeighborPosition.Top))
        assertTrue(underTest.checkNeighborValid(cellFour, NeighborPosition.Bottom))
    }

    @Test
    fun `check valid cells of cell five`() {
        val cellThree = Cell(5, 1)
        assertTrue(underTest.checkNeighborValid(cellThree, NeighborPosition.Left))
        assertFalse(underTest.checkNeighborValid(cellThree, NeighborPosition.Right))
        assertTrue(underTest.checkNeighborValid(cellThree, NeighborPosition.Top))
        assertTrue(underTest.checkNeighborValid(cellThree, NeighborPosition.Bottom))
    }

    @Test
    fun `check valid cells of cell six`() {
        val cellSix = Cell(6, 1)
        assertFalse(underTest.checkNeighborValid(cellSix, NeighborPosition.Left))
        assertTrue(underTest.checkNeighborValid(cellSix, NeighborPosition.Right))
        assertTrue(underTest.checkNeighborValid(cellSix, NeighborPosition.Top))
        assertFalse(underTest.checkNeighborValid(cellSix, NeighborPosition.Bottom))
    }

    @Test
    fun `check valid cells of cell seven`() {
        val cellThree = Cell(7, 1)
        assertTrue(underTest.checkNeighborValid(cellThree, NeighborPosition.Left))
        assertTrue(underTest.checkNeighborValid(cellThree, NeighborPosition.Right))
        assertTrue(underTest.checkNeighborValid(cellThree, NeighborPosition.Top))
        assertFalse(underTest.checkNeighborValid(cellThree, NeighborPosition.Bottom))
    }

    @Test
    fun `check valid cells of cell eight`() {
        val cellEight = Cell(8, 1)
        assertTrue(underTest.checkNeighborValid(cellEight, NeighborPosition.Left))
        assertFalse(underTest.checkNeighborValid(cellEight, NeighborPosition.Right))
        assertTrue(underTest.checkNeighborValid(cellEight, NeighborPosition.Top))
        assertFalse(underTest.checkNeighborValid(cellEight, NeighborPosition.Bottom))
    }
}