package engine.reducers.finaleReducers

import engine.objectsData.Game

class MaxTurnsReducer : IFinaleReducer {
    override fun finaleState(game: Game): Boolean {
        return game.gameData.maxTurns < game.gameData.currentTurn
    }
}