import  actionsData.Action
import reducers.ReducerManager
import reducers.activationReducers.IActivationReducer
import reducers.activationReducers.MoveActionReducer
import reducers.finaleReducers.SoldierCreationReducer
import reducers.finaleReducers.TurnsReducer
import reducers.validationReducers.DuplicateActionsReducer
import reducers.validationReducers.IValidationReducer
import reducers.validationReducers.MoveValidationReducer
import objectsData.Game

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
            MoveValidationReducer()
        ) as List<IValidationReducer<Action>>,

        listOf(MoveActionReducer() as (IActivationReducer<Action>)),

        listOf(
            SoldierCreationReducer(),
            TurnsReducer()
        )
    )

    override fun runTurn(game: Game, actions: List<Action>): Game {

        val validActions = reducerManager.validateState(game, actions)
        reducerManager.updateState(game, validActions)
        reducerManager.finaleState(game)

        return game
    }
}