package main

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import main.objects.actions.Action
import main.objects.actions.ActionDeserializer
import objects.actions.ActionsObject

class GameJsonParser {
    private val gsonBuilder = GsonBuilder()
    val gsonParser: Gson

    init {
        gsonBuilder.registerTypeAdapter(Action::class.java, ActionDeserializer())
        gsonParser = gsonBuilder.create()
    }

    fun parseToGameData(jsonString: String): Engine {
        return gsonParser.fromJson(jsonString, Engine::class.java)
    }

    fun parseToActions(jsonString: String): List<Action> {
        return gsonParser.fromJson(jsonString, ActionsObject::class.java).actions
    }
}
