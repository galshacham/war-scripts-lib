package rules.interfaces

import objectsData.Game

interface IApplyReducer {
    fun applyState(game: Game)
}