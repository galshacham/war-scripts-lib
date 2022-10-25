import actionsData.Action
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import objectsData.Game

class JsonHandler(private val json: Json) : ITranslator {

    override fun gameToBuffer(abstractGame: AbstractGame): ByteArray = json.encodeToString(abstractGame as Game).toByteArray()

    override fun bufferToGame(buffer: ByteArray): Game = json.decodeFromString(String(buffer))

    override fun bufferToActionList(buffer: ByteArray): List<Action> = json.decodeFromString(String(buffer))
}