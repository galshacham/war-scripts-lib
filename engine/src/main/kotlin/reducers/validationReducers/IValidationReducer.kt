package reducers.validationReducers

import actionsData.Action
import objectsData.Game

interface IValidationReducer<in ACTION: Action> {
    fun validate(game: Game, actions: List<ACTION>): List<Action>
}