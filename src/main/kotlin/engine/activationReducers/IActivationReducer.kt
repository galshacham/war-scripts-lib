package engine.activationReducers

import engine.actionsData.Action
import engine.objectsData.Game

// TODO: In the future
//interface IActivationReducer<T : ActionData> {
//    fun activate(gameData: GameData, action: T): List<ActionData>
//}

interface IActivationReducer<ACTION_TYPE : Action> {
    fun update(game: Game, actions: List<ACTION_TYPE>)
}