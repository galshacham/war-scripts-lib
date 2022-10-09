package reducers.finaleReducers

import objectsData.Game

class TurnsReducer : IFinaleReducer {
    override fun finaleState(game: Game) {
        game.gameData.currentTurn++
    }
}