package main.main

import main.exceptions.WrongFileFormatException
import java.io.File

val DEFAULT_SETTING_PATH: String = GameManager::class.java.classLoader.getResource("default.json").path
val JSON_SUFFIX = "json"

class GameManager {
    val mapFilePath: String
    var gameState: String = ""

    constructor(mapFilePath: String) {
        if (!mapFilePath.endsWith(JSON_SUFFIX)) {
            throw WrongFileFormatException("The settings file must be in json format!")
        }

        this.mapFilePath = mapFilePath

        gameState = File(mapFilePath).readText()
    }

    constructor() : this(DEFAULT_SETTING_PATH)

}
