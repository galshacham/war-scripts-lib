package drivers.objects

import actionsData.MoveAction
import drivers.actions.TestConstants
import objectsData.Loc

object Soldiers {
    fun aValidRangedSoldier(
        activatorId: Int = TestConstants.SOLDIER_ID_1, newLoc: Loc = TestConstants.LOC_0_0, owner: Int = TestConstants.OWNER_ID_1
    ) = MoveAction(activatorId = activatorId, newLoc = newLoc, owner = owner)

}