import actionsData.Action
import botRunner.BotRunnerFactory
import botRunner.IBotRunner
import exceptions.NoArgumentsException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import objectsData.Game

class Manager(private val runnerFactory: BotRunnerFactory, private val engine: IEngine) {
    val bots = mutableListOf<IBotRunner>()
    fun init(vararg arguments: String) {
        if (arguments.isEmpty()) {
            throw NoArgumentsException()
        }

        arguments.forEachIndexed { i, it ->
            bots.add((runnerFactory.createBotRunner(it, i)))
        }
    }


    // TODO; test it
    fun runGame(gameString: String) {
        do {
            var gameObject = Json.decodeFromString<Game>(gameString)
            val newGameString = Json.encodeToString(gameObject)
            val allActions = bots.map { Json.decodeFromString<List<Action>>(it.doTurn(newGameString)) }.flatten()
            gameObject = engine.runTurn(gameObject, allActions)
        } while (!engine.isOver(gameObject))
    }
}