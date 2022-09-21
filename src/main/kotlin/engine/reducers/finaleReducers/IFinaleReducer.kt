package engine.reducers.finaleReducers

import engine.actionsData.Action
import engine.objectsData.Game

interface IFinaleReducer {
    fun finaleState(game: Game)
}