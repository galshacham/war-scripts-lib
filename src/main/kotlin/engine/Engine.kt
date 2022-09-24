package engine

import engine.actionsData.Action
import engine.objectsData.Game
import engine.objectsData.GameResults
import engine.reducers.ReducerManager
import engine.reducers.activationReducers.IActivationReducer
import engine.reducers.activationReducers.MoveActionReducer
import engine.reducers.finaleReducers.MaxTurnsReducer
import engine.reducers.validationReducers.DuplicateActionsReducer

class Engine {
    fun runTurn(game: Game, actions: List<Action>): Game {
        val reducerManager = ReducerManager(
            listOf(DuplicateActionsReducer()),
            listOf(MoveActionReducer() as (IActivationReducer<Action>)),
            listOf(MaxTurnsReducer())
        )

        reducerManager.validateState(game, actions)
        reducerManager.updateState(game, actions)
        reducerManager.finaleState(game)

        return game
    }
}