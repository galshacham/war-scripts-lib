package main.objects.actions

import Game
import exceptions.ActionValidationException

interface Action {
    val actionId: String
    val side: Int

    fun apply(game: Game)

    fun validate(game: Game) {
        if (!game.mapData.players.contains(side))
            throw ActionValidationException("Invalid action! player of side $side does not exist!")
    }
}