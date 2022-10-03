package reducers.activationReducers

import actionsData.Action
import objectsData.Game

interface IActivationReducer<ACTION_TYPE : Action> {
    fun update(game: Game, actions: List<ACTION_TYPE>)
}