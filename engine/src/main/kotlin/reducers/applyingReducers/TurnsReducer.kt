package reducers.applyingReducers

import objectsData.Game

class TurnsReducer : IApplyReducer {
    override fun applyState(game: Game) {
        game.gameData.currentTurn++
    }
}