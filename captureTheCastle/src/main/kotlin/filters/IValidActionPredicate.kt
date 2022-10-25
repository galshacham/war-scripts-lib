package filters

import actionsData.Action
import objectsData.Game

interface IValidActionPredicate {
    fun isValidAction(game: Game, action: Action): Boolean
    fun logError(action: Action): String
}