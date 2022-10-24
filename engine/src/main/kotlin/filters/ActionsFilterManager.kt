package filters

import actionsData.Action
import objectsData.Game

class ActionsFilterManager(val predicates: List<IValidActionPredicate>) {
    fun filterActions(game: Game, actions: List<Action>): List<Action> =
        actions.filter { validate(game, it) }

    private fun validate(game: Game, action: Action): Boolean {
        predicates.forEach {
            if (!it.isValidAction(game, action)) {
                it.logError(action)
                return false
            }
        }

        return true
    }
}
