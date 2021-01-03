package main

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import main.objects.actions.Action
import main.objects.actions.ActionDeserializer

class GameJsonParser {
    private val gsonBuilder = GsonBuilder()
    private val gsonParser: Gson

    init {
        gsonBuilder.registerTypeAdapter(Action::class.java, ActionDeserializer())
        gsonParser = gsonBuilder.create()
    }

    fun parseToGameData(jsonString: String): Game {
        return gsonParser.fromJson(jsonString, Game::class.java)
    }
}
