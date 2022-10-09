import  actionsData.Action
import objectsData.*
import reducers.ReducerManager
import reducers.updateReducers.IUpdateReducer
import reducers.updateReducers.MoveUpdateReducer
import reducers.applyingReducers.SoldierCreationReducer
import reducers.applyingReducers.TurnsReducer
import reducers.validatingReducers.DuplicateActionsReducer
import reducers.validatingReducers.IValidateReducer
import reducers.validatingReducers.MoveValidateReducer

class Engine : IEngine {
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
            MoveValidateReducer()
        ) as List<IValidateReducer<Action>>,

        listOf(MoveUpdateReducer() as (IUpdateReducer<Action>)),

        listOf(
            SoldierCreationReducer(),
            TurnsReducer()
        )
    )

    override fun runTurn(gameState: Game, actions: List<Action>): Game {
        val validActions = reducerManager.validateState(gameState, actions)
        reducerManager.updateState(gameState, validActions)
        reducerManager.applyState(gameState)

        return gameState
    }

    override fun isOver(gameState: Game): Boolean {
        return gameState.gameData.currentTurn > gameState.gameData.maxTurns
    }
}
