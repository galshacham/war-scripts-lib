import actionsData.Action
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import objectsData.Game

class JsonHandler(private val json: Json) {

    fun parseGameToJson(game: Game): String = json.encodeToString(game)

    fun parseJsonToGame(jsonString: String): Game = json.decodeFromString(jsonString)

    fun parseJsonToActions(jsonString: String): List<Action> = json.decodeFromString(jsonString)
}