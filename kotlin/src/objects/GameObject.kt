package objects

import Engine

interface GameObject {
    val id: String
    val side: Int
    val loc: Location

    fun updateState(game: Engine)
}
