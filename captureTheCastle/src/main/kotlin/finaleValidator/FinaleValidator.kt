package finaleValidator

import objectsData.Game

class FinaleValidator(private val validators: List<IFinaleValidator>) {
    fun validateGameOver(game: Game): Boolean {
        return validators.any { it.validateGameStatus(game) }
    }
}