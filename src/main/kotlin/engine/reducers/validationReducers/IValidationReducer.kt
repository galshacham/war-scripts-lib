package engine.reducers.validationReducers

import engine.actionsData.Action
import engine.objectsData.Game

interface IValidationReducer {
    fun validate(game: Game, actions: List<Action>): List<Action>
}