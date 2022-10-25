package rules.postRedcuers

import objectsData.Game
import rules.interfaces.IApplyReducer

class TurnsReducer : IApplyReducer {
    override fun applyState(game: Game) {
        game.gameData.currentTurn++
    }
}