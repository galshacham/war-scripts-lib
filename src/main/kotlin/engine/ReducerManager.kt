package engine

import engine.actionsData.Action
import engine.activationReducers.IActivationReducer
import engine.objectsData.Game
import engine.validationReducers.IValidationReducer

class ReducerManager(
    private val validationReducers: List<IValidationReducer>,
    private val activationReducers: List<IActivationReducer<Action>>
) {
    fun validateState(game: Game, actions: List<Action>) {
        validationReducers.forEach { it.validate(game, actions) }
    }

    fun updateState(game: Game, actions: List<Action>) {
        val newGameData = game.copy()
        activationReducers.forEach { it.update(newGameData, actions) }
    }
}
