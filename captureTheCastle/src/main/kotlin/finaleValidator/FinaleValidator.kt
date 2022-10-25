package finaleValidator

import AbstractGame
import IStatusManager
import objectsData.Game

class FinaleValidator(private val validators: List<IFinaleValidator>) : IStatusManager {
    override fun validateGameOver(abstractGameState: AbstractGame): Boolean {
        return validators.any { it.validateGameStatus(abstractGameState as Game) }
    }

}