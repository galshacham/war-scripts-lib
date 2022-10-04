package actionsData

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import objectsData.Castle
import objectsData.GameObject
import objectsData.MeleeSoldier
import objectsData.RangedSoldier

object ActionModuleSerializer : JsonContentPolymorphicSerializer<Action>(Action::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<out Action> {
        return when (element.jsonObject["type"]?.jsonPrimitive?.content) {
            "move" -> MoveAction.serializer()
            else -> throw Exception("Unknown Action: key 'type' not found or does not matches any module type")
        }
    }
}


@Serializable(with = ActionModuleSerializer::class)
abstract class Action {
    abstract val type: String
    abstract val activatorId: Int
}