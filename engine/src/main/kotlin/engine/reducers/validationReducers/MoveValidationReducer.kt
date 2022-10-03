package engine.reducers.validationReducers

import actionsData.Action
import actionsData.MoveAction
import objectsData.Game
import objectsData.Soldier

class MoveValidationReducer : IValidationReducer<MoveAction> {
    override fun validate(game: Game, actions: List<MoveAction>): List<Action> {
        return actions.filter {
            val soldier = game.objects[it.activatorId] as Soldier

            val isValidMove = it.newLoc.inRange(soldier.loc, soldier.speed)
            print("Error: ignored action from activator: [${it.activatorId}], can not move more than ${soldier.speed}")

            isValidMove
        }
    }
}
