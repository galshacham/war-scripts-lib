package reducers.validatingReducers

import GameConstants.Companion.LOYAL_AFFECTION_RANGE
import actionsData.Action
import actionsData.CaptureAction
import objectsData.Game

class CaptureValidateReducer : IValidateReducer<CaptureAction> {
    override fun validate(game: Game, actions: List<CaptureAction>): List<Action> {
        return actions.filter {
            game.objects[it.idToCapture]!!.loc.inRange(game.objects[it.activatorId]!!.loc, LOYAL_AFFECTION_RANGE)
        }
    }
}