package com.motional.rubiksquare.data

data class Cell(
    val pos: Int,
    var numToDisplay: Int,
    var state: CellState = CellState.None
)

enum class CellState { None, Selected, NeigborSelected }