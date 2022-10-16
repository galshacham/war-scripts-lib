package rules.interfaces

import actionsData.Action
import objectsData.Game

interface IValidateReducer<in ACTION: Action> {
    fun validate(game: Game, actions: List<ACTION>): List<Action>
}