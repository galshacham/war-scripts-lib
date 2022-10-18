import  actionsData.Action
import finaleValidator.FinaleValidator
import finaleValidator.validators.TurnsOverValidator
import objectsData.*
import rules.reducers.CaptureReducerOld
import rules.ReducerManager
import rules.interfaces.IUpdateReducer
import rules.postRedcuers.SoldierCreationReducer
import rules.postRedcuers.TurnsReducer
import rules.preReducers.DuplicateActionsReducerOld
import rules.interfaces.IValidateReducerOld
import rules.reducers.MoveReducerOld

class Engine : IEngine {
    // Note!
    /*
        Currently, the order of the reducers matter, right now the reducers are invoked this way:
        Validations - 1,2,3,4... - each validation filters on all the remaining validations
        Activations - 1,2,3,4... - each activation applies changes of specific action type for all actions
        Finalizing - 1,2,3,4.... - each finalization applies more changes needed to be done for the next turn
     */
    private val moveReducer = MoveReducerOld()
    private val captureReducer = CaptureReducerOld()

    // TODO: add captures and test for this
    private val reducerManager: ReducerManager = ReducerManager(
        listOf(
            DuplicateActionsReducerOld(),
            moveReducer,
            captureReducer
        ) as List<IValidateReducerOld<Action>>,

        listOf(moveReducer as (IUpdateReducer<Action>),
            captureReducer as (IUpdateReducer<Action>)),

        listOf(
            captureReducer,
            SoldierCreationReducer(),
            TurnsReducer()
        )
    )
    private val finaleValidator: FinaleValidator = FinaleValidator(
        listOf(
            TurnsOverValidator()
        )
    )

    override fun runTurn(gameState: Game, actions: List<Action>): Game {
        // TODO here I need to add the owner for validations since someone can just push actions himself as another owner
        val validActions = reducerManager.validateState(gameState, actions)
        reducerManager.updateState(gameState, validActions)
        reducerManager.applyState(gameState)

        return gameState
    }

    override fun isOver(gameState: Game): Boolean {
        return finaleValidator.validateGameOver(gameState)
    }
}
