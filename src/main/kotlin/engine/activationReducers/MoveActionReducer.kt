package engine.activationReducers

import engine.actionsData.Action
import engine.actionsData.MoveAction
import engine.objectsData.Game
import engine.objectsData.Loc

class MoveActionReducer : IActivationReducer<MoveAction> {
    override fun update(game: Game, actions: List<MoveAction>) {
        actions.forEach {
            val a = game.objects.find { obj -> obj.id == it.activatorId }
            a!!.loc = it.newLoc
        }
    }
}