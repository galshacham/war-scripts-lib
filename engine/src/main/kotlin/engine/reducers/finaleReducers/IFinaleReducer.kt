package engine.reducers.finaleReducers

import objectsData.Game

interface IFinaleReducer {
    fun finaleState(game: Game): Boolean
}