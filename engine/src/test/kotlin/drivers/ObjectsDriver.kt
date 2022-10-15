package drivers

import GameConstants.Companion.MAX_LOYALTY
import enums.ObjectTypeEnum
import objectsData.Castle
import objectsData.Loc
import objectsData.MeleeSoldier
import objectsData.RangedSoldier

object ObjectsDriver {
    /*
    C1 - Castle1
    R1 - RangedSoldier1
    M2 - MeleeSoldier2

    ROWS
    .
    2
    1  C1 R1 M2
    0  1  2  3  4 ...COLS
     */


    fun aCastle(
        id: Int = DriversTestConstants.CASTLE_ID,
        loc: Loc = DriversTestConstants.CASTLE_LOC,
        owner: Int = DriversTestConstants.OWNER_ID,
        loyalty: Int = MAX_LOYALTY,
        soldierType: ObjectTypeEnum = ObjectTypeEnum.MELEE
    ) = Castle(id = id, loc = loc, owner = owner, loyalty = loyalty, soldierType = soldierType)

    fun aRangedSoldier(
        id: Int = DriversTestConstants.RANGED_SOLDIER_ID,
        loc: Loc = DriversTestConstants.MELEE_SOLDIER_LOC,
        owner: Int = DriversTestConstants.OWNER_ID
    ) = RangedSoldier(id = id, loc = loc, owner = owner)

    fun aMeleeSoldier(
        id: Int = DriversTestConstants.MELEE_SOLDIER_ID,
        loc: Loc = DriversTestConstants.RANGED_SOLDIER_LOC,
        owner: Int = DriversTestConstants.OWNER_ID
    ) = MeleeSoldier(id = id, loc = loc, owner = owner)

}