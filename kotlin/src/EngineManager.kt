import main.GameJsonParser
import main.exceptions.ArgumentsException
import objects.Results
import steamers.GameStreamerFactory
import steamers.GameStreamerInterface
import java.io.File

val DEFAULT_SETTING_PATH: String = EngineManager::class.java.classLoader.getResource("default.json").path
const val JSON_SUFFIX = "json"

class EngineManager(args: Array<String>, gameStreamerFactory: GameStreamerFactory, private val parser: GameJsonParser) {
    private val streamers = mutableListOf<GameStreamerInterface>()
    private val mapFilePath: String
    var gameState = ""

    init {
        if (args.isEmpty()) {
            throw ArgumentsException("Game must have at least one argument! default configuration requires: [code1, code2] format. for different configuration, add config.json as the last argument")
        }
        addStreamersToEngine(args, gameStreamerFactory)
        this.mapFilePath = getMapFile(args)
        gameState = File(mapFilePath).readText()
    }

    private fun addStreamersToEngine(args: Array<String>, gameStreamerFactory: GameStreamerFactory) {
        args.forEachIndexed { index, arg ->
            if (!arg.endsWith(JSON_SUFFIX))
                streamers.add(gameStreamerFactory.createStreamer(arg, index))
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
            streamers.forEachIndexed { side, streamer ->
                val actions = streamer.callStreamer(gameState, parser)
                game.updateData(actions)
                gameState = parser.gsonParser.toJson(game)
            }
        }

        return Results()
    }
}