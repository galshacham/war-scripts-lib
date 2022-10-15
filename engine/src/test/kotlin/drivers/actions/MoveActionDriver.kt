package drivers.actions

import actionsData.MoveAction
import drivers.TestConstants.LOC_0_0
import drivers.TestConstants.OWNER_ID_1
import drivers.TestConstants.SOLDIER_ID_1
import objectsData.Loc

object MoveActionDriver {
    fun aMoveAction(
        activatorId: Int = SOLDIER_ID_1, newLoc: Loc = LOC_0_0, owner: Int = OWNER_ID_1
    ) = MoveAction(activatorId = activatorId, newLoc = newLoc, owner = owner)


}
