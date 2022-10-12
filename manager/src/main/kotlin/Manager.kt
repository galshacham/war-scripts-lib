import actionsData.Action
import botRunner.BotRunnerFactory
import botRunner.IBotRunner
import exceptions.NoArgumentsException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import objectsData.Game
import java.util.*

class Manager(
    private val runnerFactory: BotRunnerFactory,
    private val engine: IEngine,
    private val jsonHandler: JsonHandler
) {
    val bots = mutableListOf<IBotRunner>()
    fun init(vararg arguments: String) {
        if (arguments.isEmpty()) {
            throw NoArgumentsException()
        }

        arguments.forEachIndexed { i, it ->
            bots.add((runnerFactory.createBotRunner(it, i)))
        }
    }

    fun runGame(gameString: String) {
        var gameObject = jsonHandler.parseJsonToGame(gameString)

        while (!engine.isOver(gameObject)) {
//            if (gameObject.gameData.currentTurn % 20 == 0) println("turn ${gameObject.gameData.currentTurn} ${Date()}")
            val newGameString = jsonHandler.parseGameToJson(gameObject)
            val allActions = bots.map { jsonHandler.parseJsonToActions(it.doTurn(newGameString)) }.flatten()
            gameObject = engine.runTurn(gameObject, allActions)
        }
    }
}