package engineTests.reducerTests.applyingRedcuers

import GameConstants.Companion.MAX_LOYALTY
import drivers.GameDriver.aGame
import drivers.ObjectsDriver.aCastle
import drivers.ObjectsDriver.aMeleeSoldier
import drivers.TestConstants.CASTLE_ID_1
import drivers.TestConstants.MELEE_SOLDIER_ID_1
import drivers.TestConstants.MELEE_SOLDIER_ID_2
import drivers.TestConstants.MELEE_SOLDIER_ID_3
import drivers.TestConstants.OWNER_ID_1
import drivers.TestConstants.OWNER_ID_2
import drivers.TestConstants.OWNER_ID_3
import objectsData.Loc
import org.junit.jupiter.api.Test
import reducers.applyingReducers.CaptureApplyReducer
import kotlin.test.assertEquals

class CaptureReducerTests {
    private val captureApplyReducer = CaptureApplyReducer()
    private val castleLoc = Loc(2, 2)

    @Test()
    fun `WHEN castle's loyalty went below zero SHOULD change castle's owner to nearby soldier's owner`() {
        val soldier1Loc = castleLoc.plusCols(1)
        val game = aGame(
            aCastle(id = CASTLE_ID_1, loyalty = -1, owner = OWNER_ID_1, loc = castleLoc),
            aMeleeSoldier(owner = OWNER_ID_2, loc = soldier1Loc)
        )
        val expectedGame = aGame(
            aCastle(id = CASTLE_ID_1, loyalty = MAX_LOYALTY, owner = OWNER_ID_2, loc = castleLoc),
            aMeleeSoldier(owner = OWNER_ID_2, loc = soldier1Loc)
        )

        captureApplyReducer.applyState(game)

        assertEquals(expectedGame, game)
    }

    @Test()
    fun `GIVEN soldiers of different owners WHEN castle's loyalty went below zero SHOULD change castle's owner to nearby soldier's owner most populated army`() {
        val soldier1Loc = castleLoc.plusCols(1)
        val soldier2Loc = castleLoc.plusCols(-1)
        val soldier3Loc = castleLoc.plusCols(1)
        val game = aGame(
            aCastle(id = CASTLE_ID_1, loyalty = -1, owner = OWNER_ID_1, loc = castleLoc),
            aMeleeSoldier(id = MELEE_SOLDIER_ID_1, owner = OWNER_ID_2, loc = soldier1Loc),
            aMeleeSoldier(id = MELEE_SOLDIER_ID_2, owner = OWNER_ID_2, loc = soldier2Loc),
            aMeleeSoldier(id = MELEE_SOLDIER_ID_3, owner = OWNER_ID_3, loc = soldier3Loc)
        )
        val expectedGame = aGame(
            aCastle(id = CASTLE_ID_1, loyalty = MAX_LOYALTY, owner = OWNER_ID_2, loc = castleLoc),
            aMeleeSoldier(id = MELEE_SOLDIER_ID_1, owner = OWNER_ID_2, loc = soldier1Loc),
            aMeleeSoldier(id = MELEE_SOLDIER_ID_2, owner = OWNER_ID_2, loc = soldier2Loc),
            aMeleeSoldier(id = MELEE_SOLDIER_ID_3, owner = OWNER_ID_3, loc = soldier3Loc)
        )

        captureApplyReducer.applyState(game)

        assertEquals(expectedGame, game)
    }

    @Test()
    fun `GIVEN even amount soldiers of different owners WHEN castle's loyalty went below zero SHOULD not change castle owner`() {
        val soldier1Loc = castleLoc.plusCols(1)
        val soldier2Loc = castleLoc.plusCols(-1)


        val game = aGame(
            aCastle(id = CASTLE_ID_1, loyalty = -1, owner = OWNER_ID_1, loc = castleLoc),
            aMeleeSoldier(id = MELEE_SOLDIER_ID_1, owner = OWNER_ID_2, loc = soldier1Loc),
            aMeleeSoldier(id = MELEE_SOLDIER_ID_2, owner = OWNER_ID_3, loc = soldier2Loc)
        )
        val expectedGame = aGame(
            aCastle(id = CASTLE_ID_1, loyalty = -1, owner = OWNER_ID_1, loc = castleLoc),
            aMeleeSoldier(id = MELEE_SOLDIER_ID_1, owner = OWNER_ID_2, loc = soldier1Loc),
            aMeleeSoldier(id = MELEE_SOLDIER_ID_2, owner = OWNER_ID_3, loc = soldier2Loc)
        )

        captureApplyReducer.applyState(game)

        assertEquals(expectedGame, game)
    }
}