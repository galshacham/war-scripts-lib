package finaleValidator.validators

import finaleValidator.IFinaleValidator
import objectsData.Game

class TurnsOverValidator: IFinaleValidator {
    override fun validateGameStatus(game: Game): Boolean {
        return game.gameData.currentTurn > game.gameData.maxTurns
    }
}