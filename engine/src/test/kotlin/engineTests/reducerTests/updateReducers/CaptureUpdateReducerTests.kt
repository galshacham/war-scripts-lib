package engineTests.reducerTests.updateReducers

import GameConstants.Companion.LOYAL_AFFECTION_VALUE
import drivers.ActionsDrivers.aCaptureAction
import drivers.GameDriver
import drivers.ObjectsDriver.aCastle
import drivers.ObjectsDriver.aMeleeSoldier
import drivers.TestConstants.CASTLE_ID_1
import drivers.TestConstants.MELEE_SOLDIER_ID_1
import drivers.TestConstants.MELEE_SOLDIER_ID_2
import drivers.TestConstants.MELEE_SOLDIER_ID_3
import objectsData.*
import org.junit.jupiter.api.Test
import reducers.CaptureReducer
import kotlin.test.assertEquals

class CaptureReducerTests {
    private val captureValidateReducer = CaptureReducer()

    @Test
    fun `WHEN soldier affecting loyalty of castle SHOULD affect castle change castle's loyalty`() {
        val currentGame = GameDriver.aGame(
            aMeleeSoldier(id = MELEE_SOLDIER_ID_1) as Soldier,
            aCastle(id = CASTLE_ID_1, loyalty = 10)
        )

        val expectedGame = GameDriver.aGame(
            aMeleeSoldier(id = MELEE_SOLDIER_ID_1) as Soldier,
            aCastle(id = CASTLE_ID_1, loyalty = 10 - LOYAL_AFFECTION_VALUE)
        )

        val actions = listOf(
            aCaptureAction(activatorId = MELEE_SOLDIER_ID_1),
        )

        captureValidateReducer.update(currentGame, actions)

        assertEquals(expectedGame, currentGame)
    }

    @Test
    fun `WHEN 3 soldiers affecting loyalty of castle SHOULD affect castle change castle's loyalty by 3`() {
        val currentGame = GameDriver.aGame(
            aMeleeSoldier(id = MELEE_SOLDIER_ID_1) as Soldier,
            aMeleeSoldier(id = MELEE_SOLDIER_ID_2) as Soldier,
            aMeleeSoldier(id = MELEE_SOLDIER_ID_3) as Soldier,
            aCastle(id = CASTLE_ID_1, loyalty = 10)
        )

        val expectedGame = GameDriver.aGame(
            aMeleeSoldier(id = MELEE_SOLDIER_ID_1) as Soldier,
            aMeleeSoldier(id = MELEE_SOLDIER_ID_2) as Soldier,
            aMeleeSoldier(id = MELEE_SOLDIER_ID_3) as Soldier,
            aCastle(id = CASTLE_ID_1, loyalty = 10 - 3 * LOYAL_AFFECTION_VALUE)
        )

        val actions = listOf(
            aCaptureAction(activatorId = MELEE_SOLDIER_ID_1),
            aCaptureAction(activatorId = MELEE_SOLDIER_ID_2),
            aCaptureAction(activatorId = MELEE_SOLDIER_ID_3),
        )

        captureValidateReducer.update(currentGame, actions)

        assertEquals(expectedGame, currentGame)
    }
}