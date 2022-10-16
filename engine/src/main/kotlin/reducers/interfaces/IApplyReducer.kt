package reducers.interfaces

import objectsData.Game

interface IApplyReducer {
    fun applyState(game: Game)
}