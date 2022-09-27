package engine

import engine.actionsData.Action
import engine.objectsData.Game
import engine.reducers.ReducerManager
import engine.reducers.activationReducers.IActivationReducer
import engine.reducers.activationReducers.MoveActionReducer
import engine.reducers.finaleReducers.TurnsReducer
import engine.reducers.validationReducers.DuplicateActionsReducer
import engine.reducers.validationReducers.IValidationReducer
import engine.reducers.validationReducers.MoveValidationReducer

class Engine {
    fun runTurn(game: Game, actions: List<Action>): Game {
        val validationReducers = listOf(DuplicateActionsReducer(), MoveValidationReducer()) as List<IValidationReducer<Action>>
        val reducerManager = ReducerManager(
            validationReducers,
            listOf(MoveActionReducer() as (IActivationReducer<Action>)),
            listOf(TurnsReducer())
        )

        val validActions = reducerManager.validateState(game, actions)
        reducerManager.updateState(game, validActions)
        reducerManager.finaleState(game)

        return game
    }
}