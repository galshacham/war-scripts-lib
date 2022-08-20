package objects.actions

import enums.ActionTypeEnum
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import objects.Game

@Serializable
@SerialName("Action")
abstract class Action {
    abstract val activatorId: Int
    abstract val actionType: ActionTypeEnum

    //    val actionType: ActionTypeEnum
//    val activatorId: Int
    abstract fun apply(game: Game)
    abstract fun validate(game: Game)
//        if (!game.mapData.players.contains(side))
//            throw ActionValidationException("Invalid action! player of side $side does not exist!")

}