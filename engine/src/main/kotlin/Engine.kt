import  actionsData.Action
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import objectsData.*
import reducers.ReducerManager
import reducers.activationReducers.IActivationReducer
import reducers.activationReducers.MoveActionReducer
import reducers.finaleReducers.SoldierCreationReducer
import reducers.finaleReducers.TurnsReducer
import reducers.validationReducers.DuplicateActionsReducer
import reducers.validationReducers.IValidationReducer
import reducers.validationReducers.MoveValidationReducer

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

    fun runTurn(game: Game, actions: List<Action>): Game {
        val copiedGame = deepCopy(game)

        val validActions = reducerManager.validateState(copiedGame, actions)
        reducerManager.updateState(copiedGame, validActions)
        reducerManager.finaleState(copiedGame)

        return copiedGame
    }


    override fun runTurn(gameState: String, botsActions: List<String>): String {
        val game = Json.decodeFromString<Game>(gameState)
        val actions: List<List<Action>> = botsActions.map {
            Json.decodeFromString(it)
        }

        val newGame = this.runTurn(game, actions.flatten())

        return Json.encodeToString(newGame)
    }

    override fun isOver(gameState: String): Boolean {
        TODO("Not yet implemented")
    }

    // TODO: This is the most hideous way to deep copy, and I hope i don't need it in the future, but for now
    // This helps me save the in mutation of objects
    private fun deepCopy(game: Game) = Json.decodeFromString<Game>(Json.encodeToString(game))
}
