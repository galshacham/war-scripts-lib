import Utils.Companion.getFileSuffix
import exceutors.GameExecutorFactory
import exceutors.GameExecutorInterface
import main.exceptions.ArgumentsException
import objects.Results
import java.io.File

val DEFAULT_SETTING_PATH: String = EngineManager::class.java.classLoader.getResource("default.json").path
val JSON_SUFFIX = "json"

class EngineManager {
    val executors = mutableListOf<GameExecutorInterface>()
    val mapFilePath: String
    var gameState: String = ""


    constructor(args: Array<String>, gameExecutorFactory: GameExecutorFactory) {
        if (args.size == 0) {
            throw ArgumentsException("Game must have at least one argument! default configuration requires: [code1, code2] format. for different configuration, add config.json as the last argument")
        }
        addExecutorsToEngine(args, gameExecutorFactory)

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


        return Results()
    }
}
