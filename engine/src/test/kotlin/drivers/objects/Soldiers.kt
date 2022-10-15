package drivers.objects

import drivers.TestConstants
import objectsData.Loc
import objectsData.MeleeSoldier
import objectsData.RangedSoldier

object Soldiers {
    fun aRangedSoldier(
        id: Int = TestConstants.SOLDIER_ID_1, loc: Loc = TestConstants.LOC_0_0, owner: Int = TestConstants.OWNER_ID_1
    ) = RangedSoldier(id = id, loc = loc, owner = owner)

    fun aMeleeSoldier(
        id: Int = TestConstants.SOLDIER_ID_2, loc: Loc = TestConstants.LOC_0_0, owner: Int = TestConstants.OWNER_ID_1
    ) = MeleeSoldier(id = id, loc = loc, owner = owner)
}