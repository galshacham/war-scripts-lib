package main

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import exceptions.WrongFileFormatException
import main.objects.Game
import main.objects.actions.Action
import main.objects.actions.ActionDeserializer
import java.io.File

class GameJsonParser {
    private val jsonSuffix = "json"
    private val gson = Gson()
    private val SETTINGS_PATH = "src/tests/resources/default.json"
    private var jsonString: String

    constructor (filePath: String) {
        if (!filePath.endsWith(jsonSuffix)) {
            throw WrongFileFormatException("The settings file must be in json format!")
        }

        jsonString = File(filePath).readText()
    }

    constructor() {
        jsonString = File(SETTINGS_PATH).readText()
    }

    fun getGameData(): Game {
        val gb = GsonBuilder()
        gb.registerTypeAdapter(Action::class.java, ActionDeserializer())
        val g = gb.create()
        return g.fromJson(jsonString, Game::class.java)
    }
}
