package reducers.finaleReducers

import objectsData.Game

class TurnsReducer : IFinaleReducer {
    override fun finaleState(game: Game): Boolean {
        return if (game.gameData.currentTurn < game.gameData.maxTurns) {
            game.gameData.currentTurn++
            false
        } else
            true
    }
}