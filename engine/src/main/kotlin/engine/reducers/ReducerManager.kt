package engine.reducers

import actionsData.Action
import engine.reducers.activationReducers.IActivationReducer
import engine.reducers.finaleReducers.IFinaleReducer
import engine.reducers.validationReducers.IValidationReducer
import objectsData.Game

class ReducerManager(
    private val validationReducers: List<IValidationReducer<Action>>,
    private val activationReducers: List<IActivationReducer<Action>>,
    private val finaleReducers: List<IFinaleReducer>
) {
    fun validateState(game: Game, actions: List<Action>): List<Action> {
        var filteredActions = actions
        validationReducers.forEach { filteredActions = it.validate(game, filteredActions) }

        return filteredActions
    }

    // TODO: Maybe change actions to action
    // For now, lets assume that we do not need to deep copy the game
    fun updateState(game: Game, actions: List<Action>) {
        activationReducers.forEach { it.update(game, actions) }
    }

    fun finaleState(game: Game) {
        finaleReducers.forEach { it.finaleState(game) }
    }
}