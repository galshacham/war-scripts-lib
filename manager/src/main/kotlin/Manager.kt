import botRunner.BotRunnerFactory
import botRunner.IBotRunner
import exceptions.NoArgumentsException
import java.util.Date

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

    fun runGame(gameState: String) {
        var newGameState = gameState
        while (!engine.isOver()) {
            val actions = bots.map { it.doTurn(newGameState) }

            newGameState = engine.runTurn(newGameState, actions)
        }
    }

}