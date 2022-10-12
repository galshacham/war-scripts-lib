package objectsData

import kotlin.math.abs

@kotlinx.serialization.Serializable
data class Loc(val col: Int, val row: Int) {
    fun inRange(loc: Loc, length: Int): Boolean {
        return abs(this.col - loc.col) + abs(this.row - loc.row) <= length
    }
}