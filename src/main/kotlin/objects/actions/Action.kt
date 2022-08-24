package objects.actions

import ActionSerializer
import enums.ActionTypeEnum
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import objects.Game

// This must be an abstract class that acts like an interface since the serialization of data classes with inheritance
// doesn't work the best way right now :(
@Serializable(with = ActionSerializer::class)
@SerialName("Action")
abstract class Action {
    abstract val activatorId: Int
    abstract val actionType: ActionTypeEnum
    abstract fun apply(game: Game)
    abstract fun validate(game: Game)
}