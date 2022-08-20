package objects.actions

import enums.ActionTypeEnum
import enums.SoldierTypeEnum
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import objects.Game


@Serializable
@SerialName("ChangeSoldierAction")
data class ChangeSoldierAction(
    override val activatorId: Int,
    override val actionType: ActionTypeEnum = ActionTypeEnum.CHANGE_SOLDIER_TYPE,
    val creatingSoldierType: SoldierTypeEnum,
) : Action {

    override fun apply(game: Game) {
//        val selectedCastle = game.gameObjects.find { it.id == affectedId }

//        if (selectedCastle == null) {
//            print("castle of id $affectedId on the side of $side does not exist, action did not occur!")
//        } else {
//            selectedCastle as Castle
//            selectedCastle.changeSoldierType(soldierType)
//        }
    }

    override fun validate(game: Game) {
//        super.validate(game)
//        val affectedCastle = game.gameObjects.find { it.id == affectedId }!!
//
//        if (affectedCastle.side != side) {
//            throw ActionValidationException("Invalid action! Trying to change soldier type of castle which does not belong to user (castleId: ${affectedCastle.id})")
//        }
    }
}