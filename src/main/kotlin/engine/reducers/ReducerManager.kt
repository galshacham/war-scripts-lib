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
    fun updateState(game: Game, actions: List<Action>) {
        val newGameData = game.copy()
        activationReducers.forEach { it.update(newGameData, actions) }
    }

    fun finaleState(game: Game) {
        val newGameData = game.copy()
        finaleReducers.forEach { it.finaleState(newGameData) }
    }
}
