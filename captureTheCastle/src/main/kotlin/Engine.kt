import  actionsData.Action
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import objectsData.*

class Engine(
    private val filterManager: IFilterManager,
    private val stateManager: IStateManager,
    private val statusManager: IStatusManager,
) : IEngine {
    // Note! TODO: change it soon
    /*
        Currently, the order of the reducers matter, right now the reducers are invoked this way:
        Validations - 1,2,3,4... - each validation filters on all the remaining validations
        Activations - 1,2,3,4... - each activation applies changes of specific action type for all actions
        Finalizing - 1,2,3,4.... - each finalization applies more changes needed to be done for the next turn
     */
    override fun runTurn(gameState: Game, actions: List<Action>): Game {
        var newGameState = Json.decodeFromString<Game>(Json.encodeToString(gameState))

        val validActions = filterManager.filterActions(newGameState, actions)

        validActions.forEach {
            stateManager.setState(newGameState, gameState, it)
        }

        // TODO add the final actions iterator --- I must find a way to test this

        return gameState
    }

    override fun isOver(gameState: Game): Boolean {
        return statusManager.validateGameOver(gameState)
    }
}
