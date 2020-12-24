package main

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import main.exceptions.WrongFileFormatException
import main.objects.Game
import main.objects.actions.Action
import main.objects.actions.ActionDeserializer
import java.io.File

val DEFAULT_SETTING_PATH = "default.json"
val JSON_SUFFIX = "json"

class GameJsonParser {
    private val gsonBuilder = GsonBuilder()
    private var jsonString: String
    private val gsonParser: Gson

    constructor() : this(DEFAULT_SETTING_PATH)
    constructor (filePath: String) {
        if (!filePath.endsWith(JSON_SUFFIX)) {
            throw WrongFileFormatException("The settings file must be in json format!")
        }

        gsonBuilder.registerTypeAdapter(Action::class.java, ActionDeserializer())
        gsonParser = gsonBuilder.create()

        jsonString = this::class.java.classLoader.getResource(filePath).readText()
    }

    fun getGameData(): Game {
        return gsonParser.fromJson(jsonString, Game::class.java)
    }
}
