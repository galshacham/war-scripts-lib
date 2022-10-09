package finaleValidator

import objectsData.Game

interface IFinaleValidator {
    fun validateGameStatus(game: Game): Boolean
}