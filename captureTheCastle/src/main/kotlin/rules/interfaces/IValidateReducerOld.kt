package rules.interfaces

import actionsData.Action
import objectsData.Game

interface IValidateReducerOld<in ACTION : Action> : IReducerOld {
    fun validate(game: Game, actions: List<ACTION>): List<Action>
}