package objects

import Game
import objects.actions.Action

interface GameObject {
    val id: Int
    val owner: Int
    val loc: Location
    val action: Action?

    fun updateState(game: Game)
}
