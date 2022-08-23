package objects.actions

import ModuleSerializer
import enums.ActionTypeEnum
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import objects.Game

// This must be an abstract class that acts like an interface since the serialization of data classes with inheritance
// doesn't work the best way right now :(
@Serializable(with = ModuleSerializer::class)
@SerialName("Action")
abstract class Action {
    abstract val activatorId: Int
    abstract val actionType: ActionTypeEnum
    abstract fun apply(game: Game)
    abstract fun validate(game: Game)
}