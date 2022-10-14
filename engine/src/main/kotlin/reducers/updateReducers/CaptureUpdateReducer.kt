package reducers.updateReducers

import GameConstants.Companion.LOYAL_AFFECTION_VALUE
import actionsData.CaptureAction
import objectsData.Castle
import objectsData.Game

class CaptureUpdateReducer : IUpdateReducer<CaptureAction> {
    override fun update(game: Game, actions: List<CaptureAction>) {
        actions.forEach {
            val castle = game.objects[it.idToCapture] as Castle

            castle.loyalty -= LOYAL_AFFECTION_VALUE
        }
    }

}