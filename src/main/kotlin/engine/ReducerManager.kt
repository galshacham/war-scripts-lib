package engine

import engine.actionsData.ActionData
import engine.objectsData.GameData
import engine.validationReducers.ValidationReducer

class ReducerManager(private val list: List<ValidationReducer>) {
    fun validateState(gameData: GameData, actions: List<ActionData>) {
        list.forEach { it -> it.validate(gameData, actions) }
    }
}
