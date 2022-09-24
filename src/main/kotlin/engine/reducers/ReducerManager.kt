package engine.reducers

import engine.actionsData.Action
import engine.reducers.activationReducers.IActivationReducer
import engine.objectsData.Game
import engine.reducers.finaleReducers.IFinaleReducer
import engine.reducers.validationReducers.IValidationReducer

class ReducerManager(
    private val validationReducers: List<IValidationReducer>,
    private val activationReducers: List<IActivationReducer<Action>>,
    private val finaleReducers: List<IFinaleReducer>
) {
    fun validateState(game: Game, actions: List<Action>) {
        validationReducers.forEach { it.validate(game, actions) }
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
