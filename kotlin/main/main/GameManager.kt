package main.main

import main.exceptions.ArgumentsException
import main.main.main.Results
import java.io.File

val DEFAULT_SETTING_PATH: String = GameManager::class.java.classLoader.getResource("default.json").path
val JSON_SUFFIX = "json"

class GameManager {
    val mapFilePath: String

    var gameState: String = ""


    constructor(args: Array<String>) {
        if (args.size == 0) {
            throw ArgumentsException("Game must have at least one argument! default configuration requires: [code1, code2] format. for different configuration, add config.json as the last argument")
        }

        this.mapFilePath = getMapFile(args)

        gameState = File(mapFilePath).readText()
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
