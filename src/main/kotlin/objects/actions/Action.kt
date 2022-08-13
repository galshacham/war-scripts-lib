package objects.actions

import Game

interface Action {
    val side: Int

    fun apply(game: Game)

    fun validate(game: Game) {
//        if (!game.mapData.players.contains(side))
//            throw ActionValidationException("Invalid action! player of side $side does not exist!")
    }
}