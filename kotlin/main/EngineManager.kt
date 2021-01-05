import Utils.Companion.getFileSuffix
import executors.GameExecutorFactory
import executors.GameExecutorInterface
import main.GameJsonParser
import main.exceptions.ArgumentsException
import objects.Results
import java.io.File

val DEFAULT_SETTING_PATH: String = EngineManager::class.java.classLoader.getResource("default.json").path
val JSON_SUFFIX = "json"

class EngineManager {
    val executors = mutableListOf<GameExecutorInterface>()
    val parser: GameJsonParser
    val mapFilePath: String
    var gameState: String = ""


    constructor(args: Array<String>, gameExecutorFactory: GameExecutorFactory, parser: GameJsonParser) {
        if (args.size == 0) {
            throw ArgumentsException("Game must have at least one argument! default configuration requires: [code1, code2] format. for different configuration, add config.json as the last argument")
        }

        addExecutorsToEngine(args, gameExecutorFactory)

        this.parser = parser
        this.mapFilePath = getMapFile(args)

        gameState = File(mapFilePath).readText()
    }

    private fun addExecutorsToEngine(args: Array<String>, gameExecutorFactory: GameExecutorFactory) {
        args.forEach { arg ->
            if (!arg.endsWith(JSON_SUFFIX))
                executors.add(gameExecutorFactory.createExecutor(getFileSuffix(arg)))
        }
    }


    private fun getMapFile(args: Array<String>): String {
        var mapFile = args.find { it.endsWith(JSON_SUFFIX) }

        if (mapFile == null)
            mapFile = DEFAULT_SETTING_PATH

        return mapFile
    }


    fun runGame(): Results {
        val game = parser.parseToGameData(gameState)

// TODO: validations should be implemented on the executors
//  Each executor returns actions?
//  Then we must validate the actions according to the game
//  Then we change the gameState according to the actions
//  Then we repeat?
        while (game.isUp()) {
            executors.forEachIndexed { side, executor ->
                val actions = executor.callExecutor(gameState, parser, side)
                game.updateData(actions)
                gameState = parser.gsonParser.toJson(game)
            }
        }

        return Results()
    }
}
