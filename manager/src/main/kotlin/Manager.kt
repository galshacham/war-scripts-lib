import botRunner.BotRunnerFactory
import botRunner.IBotRunner
import exceptions.NoArgumentsException

class Manager(
    private val runnerFactory: BotRunnerFactory,
    private val engine: IEngine,
    private val translator: ITranslator,
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

    fun runGame(gameState: ByteArray) {
        var gameObject = translator.bufferToGame(gameState)

        while (!engine.isOver(gameObject)) {
//            if (gameObject.gameData.currentTurn % 20 == 0) println("turn ${gameObject.gameData.currentTurn} ${Date()}")
            val newGameString = translator.gameToBuffer(gameObject)
            val allActions = bots.map { translator.bufferToActionList(it.doTurn(newGameString)) }.flatten()
            gameObject = engine.runTurn(gameObject, allActions)
        }
    }
}