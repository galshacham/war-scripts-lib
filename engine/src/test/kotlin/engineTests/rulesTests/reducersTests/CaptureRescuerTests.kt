package engineTests.rulesTests.reducersTests

import GameConstants.Companion.LOYAL_AFFECTION_VALUE
import GameConstants.Companion.MAX_LOYALTY
import drivers.ActionsDrivers
import drivers.GameDriver
import drivers.ObjectsDriver
import drivers.TestConstants
import objectsData.Loc
import objectsData.Soldier
import org.junit.jupiter.api.Test
import rules.reducers.CaptureReducer
import kotlin.test.assertEquals

class CaptureRescuerTests {
    private val captureReducer = CaptureReducer()

    @Test
    fun `WHEN soldier affecting loyalty on an existing castle in range SHOULD not filter action`() {
        val validAction =
            ActionsDrivers.aCaptureAction(activatorId = TestConstants.MELEE_SOLDIER_ID_1, idToCapture = TestConstants.CASTLE_ID_1)
        val game = GameDriver.aGame(
            ObjectsDriver.aMeleeSoldier(TestConstants.MELEE_SOLDIER_ID_1, loc = Loc(0, 0), owner = TestConstants.OWNER_ID_1),
            ObjectsDriver.aCastle(TestConstants.CASTLE_ID_1, loc = Loc(1, 0), owner = TestConstants.OWNER_ID_2)
        )


        val expectedActions = listOf(validAction)

        val actualActions = captureReducer.validate(game, expectedActions)

        assertEquals(expectedActions, actualActions)
    }

    @Test
    fun `WHEN soldier affecting loyalty on an existing castle out of range SHOULD filter action`() {
        val invalidAction =
            ActionsDrivers.aCaptureAction(activatorId = TestConstants.MELEE_SOLDIER_ID_1, idToCapture = TestConstants.CASTLE_ID_1)
        val game = GameDriver.aGame(
            ObjectsDriver.aMeleeSoldier(TestConstants.MELEE_SOLDIER_ID_1, loc = Loc(0, 0), owner = TestConstants.OWNER_ID_1),
            ObjectsDriver.aCastle(TestConstants.CASTLE_ID_1, loc = Loc(3, 3), owner = TestConstants.OWNER_ID_2)
        )

        val actions = listOf(invalidAction)

        val actualActions = captureReducer.validate(game, actions)

        assertEquals(listOf(), actualActions)
    }



    @Test
    fun `WHEN soldier affecting loyalty of castle SHOULD affect castle change castle's loyalty`() {
        val currentGame = GameDriver.aGame(
            ObjectsDriver.aMeleeSoldier(id = TestConstants.MELEE_SOLDIER_ID_1) as Soldier,
            ObjectsDriver.aCastle(id = TestConstants.CASTLE_ID_1, loyalty = 10)
        )

        val expectedGame = GameDriver.aGame(
            ObjectsDriver.aMeleeSoldier(id = TestConstants.MELEE_SOLDIER_ID_1) as Soldier,
            ObjectsDriver.aCastle(id = TestConstants.CASTLE_ID_1, loyalty = 10 - LOYAL_AFFECTION_VALUE)
        )

        val actions = listOf(
            ActionsDrivers.aCaptureAction(activatorId = TestConstants.MELEE_SOLDIER_ID_1),
        )

        captureReducer.update(currentGame, actions)

        assertEquals(expectedGame, currentGame)
    }

    @Test
    fun `WHEN 3 soldiers affecting loyalty of castle SHOULD affect castle change castle's loyalty by 3`() {
        val currentGame = GameDriver.aGame(
            ObjectsDriver.aMeleeSoldier(id = TestConstants.MELEE_SOLDIER_ID_1) as Soldier,
            ObjectsDriver.aMeleeSoldier(id = TestConstants.MELEE_SOLDIER_ID_2) as Soldier,
            ObjectsDriver.aMeleeSoldier(id = TestConstants.MELEE_SOLDIER_ID_3) as Soldier,
            ObjectsDriver.aCastle(id = TestConstants.CASTLE_ID_1, loyalty = 10)
        )

        val expectedGame = GameDriver.aGame(
            ObjectsDriver.aMeleeSoldier(id = TestConstants.MELEE_SOLDIER_ID_1) as Soldier,
            ObjectsDriver.aMeleeSoldier(id = TestConstants.MELEE_SOLDIER_ID_2) as Soldier,
            ObjectsDriver.aMeleeSoldier(id = TestConstants.MELEE_SOLDIER_ID_3) as Soldier,
            ObjectsDriver.aCastle(id = TestConstants.CASTLE_ID_1, loyalty = 10 - 3 * LOYAL_AFFECTION_VALUE)
        )

        val actions = listOf(
            ActionsDrivers.aCaptureAction(activatorId = TestConstants.MELEE_SOLDIER_ID_1),
            ActionsDrivers.aCaptureAction(activatorId = TestConstants.MELEE_SOLDIER_ID_2),
            ActionsDrivers.aCaptureAction(activatorId = TestConstants.MELEE_SOLDIER_ID_3),
        )

        captureReducer.update(currentGame, actions)

        assertEquals(expectedGame, currentGame)
    }


    private val castleLoc = Loc(2, 2)

    @Test()
    fun `WHEN castle's loyalty went below zero SHOULD change castle's owner to nearby soldier's owner`() {
        val soldier1Loc = castleLoc.plusCols(1)
        val game = GameDriver.aGame(
            ObjectsDriver.aCastle(id = TestConstants.CASTLE_ID_1, loyalty = -1, owner = TestConstants.OWNER_ID_1, loc = castleLoc),
            ObjectsDriver.aMeleeSoldier(owner = TestConstants.OWNER_ID_2, loc = soldier1Loc)
        )
        val expectedGame = GameDriver.aGame(
            ObjectsDriver.aCastle(id = TestConstants.CASTLE_ID_1, loyalty = MAX_LOYALTY, owner = TestConstants.OWNER_ID_2, loc = castleLoc),
            ObjectsDriver.aMeleeSoldier(owner = TestConstants.OWNER_ID_2, loc = soldier1Loc)
        )

        captureReducer.applyState(game)

        assertEquals(expectedGame, game)
    }

    @Test()
    fun `GIVEN soldiers of different owners WHEN castle's loyalty went below zero SHOULD change castle's owner to nearby soldier's owner most populated army`() {
        val soldier1Loc = castleLoc.plusCols(1)
        val soldier2Loc = castleLoc.plusCols(-1)
        val soldier3Loc = castleLoc.plusCols(1)
        val game = GameDriver.aGame(
            ObjectsDriver.aCastle(id = TestConstants.CASTLE_ID_1, loyalty = -1, owner = TestConstants.OWNER_ID_1, loc = castleLoc),
            ObjectsDriver.aMeleeSoldier(id = TestConstants.MELEE_SOLDIER_ID_1, owner = TestConstants.OWNER_ID_2, loc = soldier1Loc),
            ObjectsDriver.aMeleeSoldier(id = TestConstants.MELEE_SOLDIER_ID_2, owner = TestConstants.OWNER_ID_2, loc = soldier2Loc),
            ObjectsDriver.aMeleeSoldier(id = TestConstants.MELEE_SOLDIER_ID_3, owner = TestConstants.OWNER_ID_3, loc = soldier3Loc)
        )
        val expectedGame = GameDriver.aGame(
            ObjectsDriver.aCastle(id = TestConstants.CASTLE_ID_1, loyalty = MAX_LOYALTY, owner = TestConstants.OWNER_ID_2, loc = castleLoc),
            ObjectsDriver.aMeleeSoldier(id = TestConstants.MELEE_SOLDIER_ID_1, owner = TestConstants.OWNER_ID_2, loc = soldier1Loc),
            ObjectsDriver.aMeleeSoldier(id = TestConstants.MELEE_SOLDIER_ID_2, owner = TestConstants.OWNER_ID_2, loc = soldier2Loc),
            ObjectsDriver.aMeleeSoldier(id = TestConstants.MELEE_SOLDIER_ID_3, owner = TestConstants.OWNER_ID_3, loc = soldier3Loc)
        )

        captureReducer.applyState(game)

        assertEquals(expectedGame, game)
    }

    @Test()
    fun `GIVEN even amount soldiers of different owners WHEN castle's loyalty went below zero SHOULD not change castle owner`() {
        val soldier1Loc = castleLoc.plusCols(1)
        val soldier2Loc = castleLoc.plusCols(-1)


        val game = GameDriver.aGame(
            ObjectsDriver.aCastle(id = TestConstants.CASTLE_ID_1, loyalty = -1, owner = TestConstants.OWNER_ID_1, loc = castleLoc),
            ObjectsDriver.aMeleeSoldier(id = TestConstants.MELEE_SOLDIER_ID_1, owner = TestConstants.OWNER_ID_2, loc = soldier1Loc),
            ObjectsDriver.aMeleeSoldier(id = TestConstants.MELEE_SOLDIER_ID_2, owner = TestConstants.OWNER_ID_3, loc = soldier2Loc)
        )
        val expectedGame = GameDriver.aGame(
            ObjectsDriver.aCastle(id = TestConstants.CASTLE_ID_1, loyalty = -1, owner = TestConstants.OWNER_ID_1, loc = castleLoc),
            ObjectsDriver.aMeleeSoldier(id = TestConstants.MELEE_SOLDIER_ID_1, owner = TestConstants.OWNER_ID_2, loc = soldier1Loc),
            ObjectsDriver.aMeleeSoldier(id = TestConstants.MELEE_SOLDIER_ID_2, owner = TestConstants.OWNER_ID_3, loc = soldier2Loc)
        )

        captureReducer.applyState(game)

        assertEquals(expectedGame, game)
    }
}