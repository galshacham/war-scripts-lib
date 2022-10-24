import  actionsData.Action
import filters.ActionsFilterManager
import finaleValidator.FinaleValidator
import finaleValidator.validators.TurnsOverValidator
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import netscape.javascript.JSObject
import objectsData.*
import rules.ReducerHandler
import rules.reducers.CaptureReducerOld
import rules.interfaces.IUpdateReducer
import rules.postRedcuers.SoldierCreationReducer
import rules.postRedcuers.TurnsReducer
import rules.preReducers.DuplicateActionsReducerOld
import rules.interfaces.IValidateReducerOld
import rules.reducers.MoveReducerOld
import rules.states.StateManager

// Now that I think about it, the engine should probably implament somehow the shit it is using
// Maybe as an abstarct class
class Engine : IEngine {
    // Note! TODO: change it soon
    /*
        Currently, the order of the reducers matter, right now the reducers are invoked this way:
        Validations - 1,2,3,4... - each validation filters on all the remaining validations
        Activations - 1,2,3,4... - each activation applies changes of specific action type for all actions
        Finalizing - 1,2,3,4.... - each finalization applies more changes needed to be done for the next turn
     */
    private val actionFinaleValidator = ActionsFilterManager(listOf())

    // TODO, does these needs to be here, or be constructed for tests?
    private val reducerHandler = ReducerHandler(
        listOf(), StateManager()
    )

    private val finaleValidator: FinaleValidator = FinaleValidator(
        listOf(
            TurnsOverValidator()
        )
    )

    override fun runTurn(gameState: Game, actions: List<Action>): Game {
        // TODO here I need to add the owner for validations since someone can just push actions himself as another owner
        var newGameState = Json.decodeFromString<Game>(Json.encodeToString(gameState))

        val validActions = actionFinaleValidator.filterActions(newGameState, actions)

        validActions.forEach {
            newGameState = reducerHandler.setState(newGameState, it)
        }

        // TODO add the final actions iterator --- I must find a way to test this

        return gameState
    }

    override fun isOver(gameState: Game): Boolean {
        return finaleValidator.validateGameOver(gameState)
    }
}
