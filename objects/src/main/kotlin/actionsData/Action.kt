package actionsData

import AbstractAction
import Globals.Companion.ACTION_JSON_SERIALIZER_REF
import enums.ActionTypeEnum
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

// TODO: add serialization tests

object ActionModuleSerializer : JsonContentPolymorphicSerializer<Action>(Action::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<out Action> {
        return when (element.jsonObject[ACTION_JSON_SERIALIZER_REF]?.jsonPrimitive?.content) {
            ActionTypeEnum.MOVE.type -> MoveAction.serializer()
            else -> throw Exception("Unknown Action: key 'type' not found or does not matches any module type")
        }
    }
}


@Serializable(with = ActionModuleSerializer::class)
abstract class Action : AbstractAction() {
    abstract val type: ActionTypeEnum
    abstract val owner: Int
    abstract val activatorId: Int
}