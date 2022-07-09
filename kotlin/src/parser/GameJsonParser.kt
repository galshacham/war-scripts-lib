package parser

import Game
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import objects.actions.Action
import objects.GameObject

class GameJsonParser {
    private val gsonBuilder = GsonBuilder()
    val gsonParser: Gson

    init {
        val simpleGson = Gson()
//        gsonBuilder.registerTypeAdapter(Action::class.java, ActionDeserializer())
        gsonBuilder.registerTypeAdapter(GameObject::class.java, GameObjectDeserializer(simpleGson))
        gsonParser = gsonBuilder.create()
    }

    fun parseToGameData(jsonString: String): Game {
        return gsonParser.fromJson(jsonString, Game::class.java)
    }

    fun parseToActions(jsonString: String): List<Action> {
//        return gsonParser.fromJson(jsonString, ActionsObject::class.java).actions
        return listOf()
    }
}
