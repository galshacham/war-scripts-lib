package engine.reducers.activationReducers

import engine.actionsData.Action
import engine.objectsData.Game

interface IActivationReducer<ACTION_TYPE : Action> {
    fun update(game: Game, actions: List<ACTION_TYPE>)
}