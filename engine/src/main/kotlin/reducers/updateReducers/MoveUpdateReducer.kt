package reducers.updateReducers

import actionsData.MoveAction
import objectsData.Game

class MoveUpdateReducer : IUpdateReducer<MoveAction> {
    override fun update(game: Game, actions: List<MoveAction>) {
        actions.forEach {
            val soldier = game.objects[it.activatorId]

            soldier!!.loc = it.newLoc
        }
    }
}