package drivers

import objectsData.Loc

object DriversTestConstants {
    // This class should not be used in tests, only by default
    const val MELEE_SOLDIER_ID = 1000

    const val RANGED_SOLDIER_ID = 1001

    const val CASTLE_ID = 1002

    const val OWNER_ID = 0


    val CASTLE_LOC = Loc(1, 0)
    val MELEE_SOLDIER_LOC = Loc(2, 0)
    val RANGED_SOLDIER_LOC = Loc(3, 0)

    const val CURRENT_TURN = 20
    const val MAX_TURNS = 50
}