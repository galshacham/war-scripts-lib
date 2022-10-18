package rules

import actionsData.Action
import rules.interfaces.IUpdateReducer
import rules.interfaces.IApplyReducer
import rules.interfaces.IValidateReducerOld
import objectsData.Game

class ReducerManager(
    private val validatingReducers: List<IValidateReducerOld<Action>>,
    private val updatingReducers: List<IUpdateReducer<Action>>,
    private val applyingReducers: List<IApplyReducer>
) {
    fun validateState(game: Game, actions: List<Action>): List<Action> {
        var legalActions = actions
        validatingReducers.forEach { reducer ->
            legalActions.forEach { action ->
                if (action::class == reducer.getActionType()) {

                } else {

                }
            }
            val filteredActionsForReducer = legalActions.filter { it::class == reducer.getActionType() }
            legalActions = reducer.validate(game, filteredActionsForReducer)
        }

        return legalActions
    }

    // TODO: Maybe change actions to action
    // For now, lets assume that we do not need to deep copy the game
    fun updateState(game: Game, actions: List<Action>) {
        updatingReducers.forEach { it.update(game, actions) }
    }

    fun applyState(game: Game) {
        return applyingReducers.forEach { it.applyState(game) }
    }
}
