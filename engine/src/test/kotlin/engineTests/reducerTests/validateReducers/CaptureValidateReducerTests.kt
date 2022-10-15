package engineTests.reducerTests.validateReducers

import drivers.ActionsDrivers.aCaptureAction
import drivers.GameDriver.aGame
import drivers.ObjectsDriver.aCastle
import drivers.ObjectsDriver.aMeleeSoldier
import drivers.TestConstants.CASTLE_ID_1
import drivers.TestConstants.MELEE_SOLDIER_ID_1
import drivers.TestConstants.OWNER_ID_1
import drivers.TestConstants.OWNER_ID_2
import objectsData.Loc
import org.junit.jupiter.api.Test
import reducers.validatingReducers.CaptureValidateReducer
import kotlin.test.assertEquals

class CaptureValidateReducerTests {
    private val captureValidateReducer = CaptureValidateReducer()

    @Test
    fun `WHEN soldier affecting loyalty on an existing castle in range SHOULD not filter action`() {
        val validAction = aCaptureAction(activatorId = MELEE_SOLDIER_ID_1, idToCapture = CASTLE_ID_1)
        val game = aGame(
            aMeleeSoldier(MELEE_SOLDIER_ID_1, loc = Loc(0, 0), owner = OWNER_ID_1),
            aCastle(CASTLE_ID_1, loc = Loc(1, 0), owner = OWNER_ID_2)
        )


        val expectedActions = listOf(validAction)

        val actualActions = captureValidateReducer.validate(game, expectedActions)

        assertEquals(expectedActions, actualActions)
    }

    @Test
    fun `WHEN soldier affecting loyalty on an existing castle out of range SHOULD filter action`() {
        val invalidAction = aCaptureAction(activatorId = MELEE_SOLDIER_ID_1, idToCapture = CASTLE_ID_1)
        val game = aGame(
            aMeleeSoldier(MELEE_SOLDIER_ID_1, loc = Loc(0, 0), owner = OWNER_ID_1),
            aCastle(CASTLE_ID_1, loc = Loc(3, 3), owner = OWNER_ID_2)
        )

        val actions = listOf(invalidAction)

        val actualActions = captureValidateReducer.validate(game, actions)

        assertEquals(listOf(), actualActions)
    }
}