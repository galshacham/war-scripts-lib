package engine.activationReducers

import engine.actionsData.MoveAction
import engine.objectsData.Game

class MoveActionReducer : IActivationReducer<MoveAction> {
    override fun update(game: Game, actions: List<MoveAction>) {
        actions.forEach {
            val soldier = game.objects.find { obj -> obj.id == it.activatorId }
            soldier!!.loc = it.newLoc
        }
    }
}