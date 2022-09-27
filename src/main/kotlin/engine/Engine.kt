package engine

import engine.actionsData.Action
import engine.objectsData.Game
import engine.reducers.ReducerManager
import engine.reducers.activationReducers.IActivationReducer
import engine.reducers.activationReducers.MoveActionReducer
import engine.reducers.finaleReducers.SoldierCreationReducer
import engine.reducers.finaleReducers.TurnsReducer
import engine.reducers.validationReducers.DuplicateActionsReducer
import engine.reducers.validationReducers.IValidationReducer
import engine.reducers.validationReducers.MoveValidationReducer

class Engine {
    // Note!
    /*
        Currently, the order of the reducers matter, right now the reducers are invoked this way:
        Validations - 1,2,3,4... - each validation filters on all the remaining validations
        Activations - 1,2,3,4... - each activation applies changes of specific action type for all actions
        Finalizing - 1,2,3,4.... - each finalization applies more changes needed to be done for the next turn
     */

    private val reducerManager: ReducerManager = ReducerManager(
        listOf(
            DuplicateActionsReducer(),
            MoveValidationReducer()
        ) as List<IValidationReducer<Action>>,

        listOf(MoveActionReducer() as (IActivationReducer<Action>)),

        listOf(
            SoldierCreationReducer(),
            TurnsReducer()
        )
    )

    fun runTurn(game: Game, actions: List<Action>): Game {

        val validActions = reducerManager.validateState(game, actions)
        reducerManager.updateState(game, validActions)
        reducerManager.finaleState(game)

        return game
    }
}