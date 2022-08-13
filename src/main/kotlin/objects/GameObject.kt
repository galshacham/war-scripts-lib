package objects

import Game
import objects.actions.Action

interface GameObject<ACTION : Action> {
    val id: Int
    val owner: Int
    val loc: Location
    val action: ACTION?

    fun updateState(game: Game)
}
