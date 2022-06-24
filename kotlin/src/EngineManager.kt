import main.GameJsonParser
import main.exceptions.ArgumentsException
import objects.Results
import steamers.GameStreamerFactory
import steamers.GameStreamerInterface
import java.io.File

val DEFAULT_SETTING_PATH: String = EngineManager::class.java.classLoader.getResource("default.json").path
val JSON_SUFFIX = "json"

class EngineManager {
    val Streamers = mutableListOf<GameStreamerInterface>()
    val parser: GameJsonParser
    val mapFilePath: String
    var gameState: String = ""

    constructor(args: Array<String>, gameStreamerFactory: GameStreamerFactory, parser: GameJsonParser) {
        if (args.isEmpty()) {
            throw ArgumentsException("Game must have at least one argument! default configuration requires: [code1, code2] format. for different configuration, add config.json as the last argument")
        }

        addStreamersToEngine(args, gameStreamerFactory)

        this.parser = parser
        this.mapFilePath = getMapFile(args)

        gameState = File(mapFilePath).readText()
    }

    private fun addStreamersToEngine(args: Array<String>, gameStreamerFactory: GameStreamerFactory) {
        args.forEachIndexed { index, arg ->
            if (!arg.endsWith(JSON_SUFFIX))
                Streamers.add(gameStreamerFactory.createStreamer(arg, index))
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

        println("Started game")
        var a = 0;
        while (game.isUp()) {
            if (a % 20 == 0) {
                println("Turn $a")
            }
            a++;
            Streamers.forEachIndexed { side, streamer ->
                val actions = streamer.callStreamer(gameState, parser, side)
                game.updateData(actions)
                gameState = parser.gsonParser.toJson(game)
            }
        }

        return Results()
    }
}