package objects

import Game
import objects.actions.Action

interface GameObject {
    val id: Int
    val owner: Int
    val loc: Location

    fun updateState(game: Game)
}
