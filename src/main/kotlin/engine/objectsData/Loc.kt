package engine.objectsData

import kotlin.math.abs

@kotlinx.serialization.Serializable
data class Loc(val row: Int, val col: Int) {
    fun inRange(loc: Loc, length: Int): Boolean {
        return abs(this.col - loc.col) + abs(this.row - loc.row) <= length
    }
}