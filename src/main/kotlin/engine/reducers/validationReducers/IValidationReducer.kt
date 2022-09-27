package engine.reducers.validationReducers

import engine.actionsData.Action
import engine.objectsData.Game

interface IValidationReducer<in ACTION: Action> {
    fun validate(game: Game, actions: List<ACTION>): List<Action>
}