package objects

import Game

interface GameObject {
    val id: String
    val owner: Int
    val loc: Location

    fun updateState(game: Game)
}
