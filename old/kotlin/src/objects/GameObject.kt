package objects

import Game

interface GameObject {
    val id: String
    val side: Int
    val loc: Location

    fun updateState(game: Game)
}
