package rules.interfaces

import actionsData.Action
import objectsData.Game

interface IUpdateReducer<ACTION_TYPE : Action> {
    fun update(game: Game, actions: List<ACTION_TYPE>)
}