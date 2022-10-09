package reducers.applyingReducers

import objectsData.Game

interface IApplyReducer {
    fun applyState(game: Game)
}