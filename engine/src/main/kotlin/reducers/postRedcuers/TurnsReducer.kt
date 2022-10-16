package reducers.postRedcuers

import objectsData.Game
import reducers.interfaces.IApplyReducer

class TurnsReducer : IApplyReducer {
    override fun applyState(game: Game) {
        game.gameData.currentTurn++
    }
}