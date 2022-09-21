package engine.reducers.activationReducers

import engine.actionsData.MoveAction
import engine.exceptions.NoIdException
import engine.objectsData.Game

class MoveActionReducer : IActivationReducer<MoveAction> {
    override fun update(game: Game, actions: List<MoveAction>) {
        actions.forEach {
            val soldier = game.objects.find { obj -> obj.id == it.activatorId }

            if (soldier != null) {
                soldier.loc = it.newLoc
            } else throw NoIdException("Got id: [${it.activatorId}] from action but no game object exist with this id")
        }
    }
}