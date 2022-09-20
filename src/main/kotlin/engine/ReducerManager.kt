package engine

import engine.actionsData.ActionData
import engine.activationReducers.IActivationReducer
import engine.objectsData.GameData
import engine.validationReducers.IValidationReducer

class ReducerManager(
    private val validationReducers: List<IValidationReducer>,
    private val activationReducers: List<IActivationReducer>
) {
    fun validateState(gameData: GameData, actions: List<ActionData>) {
        validationReducers.forEach { it.validate(gameData, actions) }
    }

    fun updateState(gameData: GameData, actions: List<ActionData>) {
        val newGameData = gameData.copy()
        activationReducers.forEach { it.update(newGameData, actions) }
    }
}
