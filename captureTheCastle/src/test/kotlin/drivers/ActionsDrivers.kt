package drivers

import actionsData.CaptureAction
import actionsData.MoveAction
import drivers.TestConstants.CASTLE_ID_1
import drivers.TestConstants.LOC_2_0
import drivers.TestConstants.MELEE_SOLDIER_ID_1
import drivers.TestConstants.OWNER_ID_1
import objectsData.Loc

object ActionsDrivers {
    fun aMoveAction(
        activatorId: Int = MELEE_SOLDIER_ID_1, newLoc: Loc = LOC_2_0, owner: Int = OWNER_ID_1
    ) = MoveAction(activatorId = activatorId, newLoc = newLoc, owner = owner)

    fun aCaptureAction(
        activatorId: Int = MELEE_SOLDIER_ID_1, idToCapture: Int = CASTLE_ID_1, owner: Int = OWNER_ID_1
    ) = CaptureAction(activatorId = activatorId, idToCapture, owner = owner)


}
