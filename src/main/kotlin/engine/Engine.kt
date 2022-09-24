package engine

import engine.actionsData.Action
import engine.objectsData.Game
import engine.reducers.ReducerManager
import engine.reducers.activationReducers.IActivationReducer
import engine.reducers.activationReducers.MoveActionReducer
import engine.reducers.finaleReducers.TurnsReducer
import engine.reducers.validationReducers.DuplicateActionsReducer
import engine.reducers.validationReducers.MoveValidationReducer

class Engine {
    fun runTurn(game: Game, actions: List<Action>): Game {
        val reducerManager = ReducerManager(
            listOf(DuplicateActionsReducer(), MoveValidationReducer()),
            listOf(MoveActionReducer() as (IActivationReducer<Action>)),
            listOf(TurnsReducer())
        )

        reducerManager.validateState(game, actions)
        reducerManager.updateState(game, actions)
        reducerManager.finaleState(game)

        return game
    }
}