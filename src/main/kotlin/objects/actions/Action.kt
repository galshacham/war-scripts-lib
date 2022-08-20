package objects.actions

import enums.ActionTypeEnum
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import objects.Game

@Serializable
@SerialName("Action")
sealed interface Action {
    val activatorId: Int
    val actionType: ActionTypeEnum

    fun apply(game: Game)
    fun validate(game: Game)
//        if (!game.mapData.players.contains(side))
//            throw ActionValidationException("Invalid action! player of side $side does not exist!")

}