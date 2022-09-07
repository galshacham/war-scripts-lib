package actions

import enums.ActionTypeEnum
import enums.SoldierTypeEnum
import objects.Game

abstract class Action(
    val activatorId: Int,
    val actionType: ActionTypeEnum = ActionTypeEnum.CHANGE_SOLDIER_TYPE
) {
    abstract fun apply(game: Game)
    abstract fun validate(game: Game)
}