package engine.activationReducers

import engine.actionsData.ActionData
import engine.objectsData.GameData

// TODO: In the future
//interface IActivationReducer<T : ActionData> {
//    fun activate(gameData: GameData, action: T): List<ActionData>
//}

interface IActivationReducer {
    fun update(gameData: GameData, actions: List<ActionData>)
}