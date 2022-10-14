package drivers.actions

import actionsData.MoveAction
import drivers.actions.TestConstants.LOC_0_0
import drivers.actions.TestConstants.OWNER_ID_1
import drivers.actions.TestConstants.SOLDIER_ID_1
import enums.ActionTypeEnum
import objectsData.Loc

object MoveActionDriver {
    fun aValidMoveAction(
        activatorId: Int = SOLDIER_ID_1, newLoc: Loc = LOC_0_0, owner: Int = OWNER_ID_1
    ) = MoveAction(activatorId = activatorId, newLoc = newLoc, owner = owner)


}
