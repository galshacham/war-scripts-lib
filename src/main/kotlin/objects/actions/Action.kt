package objects.actions

import enums.ActionTypeEnum
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import objects.Game

object ModuleSerializer : JsonContentPolymorphicSerializer<Action>(Action::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<out Action> {
        return when (element.jsonObject["actionType"]?.jsonPrimitive?.content) {
            "CHANGE_SOLDIER_TYPE" -> ChangeSoldierAction.serializer()
            else -> throw Exception("Unknown Module: key 'type' not found or does not matches any module type")
        }
    }
}

@Serializable(with = ModuleSerializer::class)
@SerialName("Action")
abstract class Action {
    abstract val activatorId: Int
    abstract val actionType: ActionTypeEnum
//        get() {
//            return getType()
//        }

//    abstract fun getType(): ActionTypeEnum
    abstract fun apply(game: Game)
    abstract fun validate(game: Game)
//        if (!game.mapData.players.contains(side))
//            throw ActionValidationException("Invalid action! player of side $side does not exist!")

}